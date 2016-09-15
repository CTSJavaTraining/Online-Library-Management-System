package com.training.restservices;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.UserContactDetails;
import com.training.entity.UserDetails;
import com.training.factory.ApplicationSessionFactory;

/**
 * 
 * @author 447482 Sign up service to validate and insert NEW USER data into
 *         database
 */
@ComponentScan
@RestController
@EnableAutoConfiguration
@RequestMapping("/home")
public class SignupService {

	Logger logger = Logger.getLogger(SignupService.class);
	private static SessionFactory factory = ApplicationSessionFactory.factoryProvider();

	@RequestMapping(name = "new user details", value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setBasicDetails(@RequestBody UserDetails userdetails) {

		// First level Validation
		if (!userdetails.getUserName().trim().isEmpty()) {

			// Method
			String username = userdetails.getUserName();

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				logger.debug("User entered username is " + username);
				System.out.println("User enterted usernaem is " + username);
				
				String hql = "FROM UserDetails WHERE userName = :uName";

				Query query = session.createQuery(hql);
				query.setParameter("uName", username);

				if (query.list().isEmpty()) {
					System.out.println("Query list is empty so trying to commit ");
					List<UserContactDetails> contactDetails = userdetails.getUserContactDetails();

					for (UserContactDetails enterContactDetails : contactDetails) {
						enterContactDetails.setUserDetails(userdetails);
					}

					session.persist(userdetails);
					System.out.println("Persisted user details ");
					session.getTransaction().commit();
					System.out.println("Commited");

					return "Successfully saved your information";
				}

				else {
					return "Please try again!!";
				}
			}

		}

		else {

			return "Username should not be empty";
		}
	}

}
