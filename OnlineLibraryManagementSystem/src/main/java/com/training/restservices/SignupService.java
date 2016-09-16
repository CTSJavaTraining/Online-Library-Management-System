package com.training.restservices;

import java.util.Date;
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

import com.training.entity.AddressDetails;
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

	private static final Logger logger = Logger.getLogger(SignupService.class);

	private static SessionFactory factory = ApplicationSessionFactory.factoryProvider();
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setBasicDetails(@RequestBody UserDetails userdetails) {

		// First level Validation
		// Method
		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				
				logger.debug("User entered username is " + username);
				System.out.println("User enterted username is " + username);

				Query query = session.createQuery("FROM UserDetails WHERE userName = :uName");
				query.setParameter("uName", username);

				if (query.list().isEmpty()) {
					System.out.println("Query list is empty(No user in that name) so trying to commit ");

					List<AddressDetails> addressList = userdetails.getAddressDetails();

					for (AddressDetails address : addressList) {
						address.setUserDetails(userdetails);
						address.setCreatedTime(getCurrentDateTime());
						address.setModifiedTime(getCurrentDateTime());
					}
					
					userdetails.setcreatedTime(getCurrentDateTime());
					userdetails.setmodifiedTime(getCurrentDateTime());
					
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

	// Utility Method fot getting current date and time to store into Db
	private Date getCurrentDateTime() {
		return new Date();
	}
	
}
