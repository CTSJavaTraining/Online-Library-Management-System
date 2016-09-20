package com.training.restservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dao.UserDAO;
import com.training.entity.LibraryItems;
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
public class UserServices {

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

	SessionFactory factory = new Configuration().configure().buildSessionFactory();

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response setBasicDetails(@RequestBody UserDetails userdetails) {

		UserDAO userDao = new UserDAO();
		userDao.userSignUp(userdetails);

		String username = userdetails.getUserName();

		boolean userValidationStatus = userDao.validateUser(username);

		if (userValidationStatus == false) {
			return Response.status(Response.Status.CONFLICT).entity("User :" + username + " already exists ").build();
		} else {
			boolean signupStatus = userDao.userSignUp(userdetails);
			if (signupStatus == true) {
				return Response.status(Response.Status.OK).entity("Successfully saved your information").build();
			} else {
				return Response.status(Response.Status.BAD_GATEWAY)
						.entity("Error creating user. Please try again later!!").build();
			}
		}

	}

	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response userNameExistance(@RequestBody UserDetails userdetails) {

		String username = userdetails.getUserName();
		UserDAO userdao = new UserDAO();
		if (!username.isEmpty()) {

			boolean validationStatus = userdao.validateUser(username);

			if (validationStatus) {
				return Response.status(Response.Status.CONFLICT).entity("User exist").build();
			} else {
				return Response.status(Response.Status.OK).entity("User does not exist").build();
			}
		}

		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Username should not be blank").build();
		}

	}

	
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		logger.info("Validating user for logging in");

		String userId = loginDetails.getUserId().trim();
		String password = loginDetails.getPassword().trim();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {

			UserDAO userdao = new UserDAO();
			int loginStatus = userdao.validateLogin(userId, password);

			if (loginStatus == 0) {
				return Response.status(Response.Status.NOT_FOUND).entity("User does not exist. Please signup").build();
			} else if (loginStatus == 1) {
				return Response.status(Response.Status.OK).entity("User " + userId + " logged in successfully").build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect Login Details").build();
			}
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("userId or password should not be empty")
					.build();
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response searchService(@RequestBody LibraryItems libraryitems) {

		return Response.status(Response.Status.OK).entity("test").build();
	}

	@RequestMapping("/test")
	String home() {
		return "Hello World!";
	}

}
