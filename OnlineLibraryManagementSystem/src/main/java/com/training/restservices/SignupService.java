package com.training.restservices;

import java.util.Date;
import java.util.List;

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
public class SignupService {

	private static final Logger logger = LoggerFactory.getLogger(SignupService.class);

	SessionFactory factory = BootApplication.factory;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String setBasicDetails(@RequestBody UserDetails userdetails) {

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
		return "Successfully saved your information";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response loginService(@RequestBody UserDetails userdetails) {

		return Response.status(Response.Status.OK).entity(new UserDetails()).build();
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
