package com.training.restservices;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.AddressDetails;
import com.training.entity.LoginDetails;
import com.training.entity.UserDetails;

/**
 * 
 * @author 447482 Sign up service to validate and insert NEW USER data into
 *         database
 */

@ComponentScan
@RestController
@EnableAutoConfiguration
@RequestMapping("/new")
public class UserServices {

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

	SessionFactory factory = BootApplication.factory;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response setBasicDetails(@RequestBody UserDetails userdetails) {

		try (Session session = factory.openSession()) {
			session.beginTransaction();

			List<AddressDetails> addressList = userdetails.getAddressDetails();

			for (AddressDetails address : addressList) {
				address.setUserDetails(userdetails);
				address.setCreatedTime(getCurrentDateTime());
				address.setModifiedTime(getCurrentDateTime());

				userdetails.setcreatedTime(getCurrentDateTime());
				userdetails.setmodifiedTime(getCurrentDateTime());

				session.persist(userdetails);
				logger.info("Persisted user details ");
				session.getTransaction().commit();
				logger.info("Commited");
			}
		}
		return Response.status(Response.Status.OK).entity("Successfully saved your information").build();
	}

	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response validateUserId(@RequestBody UserDetails userdetails) {

		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			try (Session session = factory.openSession()) {
				session.beginTransaction();
				logger.debug("User entered username is {} ", username);

				Query query = session.createQuery("FROM UserDetails WHERE userName = :uName");
				query.setParameter("uName", username);
				query.setMaxResults(1);

				if (query.getResultList().isEmpty()) {
					return Response.status(Response.Status.OK).entity("User does not exist").build();
				} else {
					return Response.status(Response.Status.CONFLICT).entity("User exist").build();
				}
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Username should not be blank").build();
		}

	}

	
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		logger.info("Validating user for logging in");

		String userId = loginDetails.getUserId();
		String password = loginDetails.getPassword();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {
			try (Session session = factory.openSession()) {

				session.beginTransaction();

				String hql = "SELECT password FROM LoginDetails WHERE userId= :id";
				Query query = session.createQuery(hql);

				query.setParameter("id", userId);
				query.setMaxResults(1);

				String results = query.getResultList().get(0).toString();

				if (results.isEmpty()) {
					return Response.status(Response.Status.NOT_FOUND).entity("User does not exist. Please signup")
							.build();
				} else if (password.equalsIgnoreCase(results)) {
					return Response.status(Response.Status.OK).entity("User " + userId + " logged in successfully")
							.build();
				} else {
					return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect Login Details").build();
				}
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("userId or password should not be empty")
					.build();
		}
	}

	@RequestMapping(value = "/ ", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response userNameExistance(@RequestParam("username") String username) {

		logger.info("welcome");
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

}
