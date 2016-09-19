package com.training.restservices;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.AddressDetails;
import com.training.entity.UserDetails;

/**
 * 
 * @author 447482 Sign up service to validate and insert NEW USER data into
 *         database
 */

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class SignupService {

	private static final Logger logger = Logger.getLogger(SignupService.class);

	SessionFactory factory = returnFactory();

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String setBasicDetails(@RequestBody UserDetails userdetails) {

		// First level Validation
		// Method
		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				logger.debug("User entered username is " + username);
				System.out.println("User enterted username is " + username);

				@SuppressWarnings("unchecked")
				Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE userName = :uName");
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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Response loginService(@RequestBody UserDetails userdetails) {

                    return Response.status(Response.Status.OK).entity(new UserDetails()).build();
    }

    @RequestMapping(value = "/ ", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    private Response userNameExistance(@RequestParam("username") String username) {

                    System.out.println("welcomr");
                    return Response.status(Response.Status.OK).entity("user namae exists").build();

    }

    @RequestMapping("/test")
    String home() {
                    return "Hello World!";
    }



	// Utility Method fot getting current date and time to store into Db
	private Date getCurrentDateTime() {
		return new Date();
	}

	public static SessionFactory returnFactory() {
		logger.info("Loading sessiong factory");
		return new Configuration().configure().buildSessionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(SignupService.class, args);
		logger.info("_---------------------- Application starts--------------------");
	}

}
