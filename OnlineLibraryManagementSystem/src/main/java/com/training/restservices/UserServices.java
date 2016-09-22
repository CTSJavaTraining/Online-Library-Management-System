package com.training.restservices;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.daoimplementation.AnonymousUser;
import com.training.daoimplementation.UserDAOImpl;
import com.training.entity.LibraryItems;
import com.training.entity.LikedList;
import com.training.entity.LoginDetails;
import com.training.entity.UserDetails;

/**
 * 
 * @author 447482
 *
 *         RestController class for signup services includes inserting user
 *         signup details, user validation, login service and search items from
 *         library
 */

@ComponentScan
@RestController
@EnableAutoConfiguration
public class UserServices {

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

	@Autowired
	private UserDAOImpl userDao;

	@Autowired
	private AnonymousUser anonymousUser;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response setBasicDetails(@RequestBody UserDetails userdetails) {

		userDao.userSignUp(userdetails);

		String username = userdetails.getUserName();

		boolean userValidationStatus = userDao.validateUser(username);

		if (userValidationStatus) {
			boolean signupStatus = userDao.userSignUp(userdetails);

			if (signupStatus) {
				return Response.status(Response.Status.OK).entity("Successfully saved your information").build();
			} else {
				return Response.status(Response.Status.BAD_GATEWAY)
						.entity("Error creating user. Please try again later!!").build();
			}

		} else {
			return Response.status(Response.Status.CONFLICT).entity("User :" + username + " already exists ").build();
		}
	}

	/**
	 * 
	 * @param userdetails
	 * @return
	 */
	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response userNameExistance(@RequestBody UserDetails userdetails) {

		String username = userdetails.getUserName();

		if (!username.isEmpty()) {

			boolean validationStatus = userDao.validateUser(username);

			if (validationStatus) {
				return Response.status(Response.Status.OK).entity("User does not exist").build();
			} else {
				return Response.status(Response.Status.CONFLICT).entity("User exist").build();
			}
		}

		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("Username should not be blank").build();
		}

	}

	/**
	 * 
	 * @param loginDetails
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		logger.info("Validating user for logging in");

		String userId = loginDetails.getUserId().trim();
		String password = loginDetails.getPassword().trim();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {

			logger.info("username and password is not empty and validating user");

			boolean loginUserStatus = userDao.validateLoginUser(userId);

			if (loginUserStatus) {
				return Response.status(Response.Status.NOT_FOUND).entity("User does not exist. Please signup").build();
			} else {

				boolean loginStatus = userDao.validateLogin(userId, password);

				if (loginStatus) {
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

	/**
	 * 
	 * @param likedList
	 * @return
	 */
	@RequestMapping(value = "/likeitems", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response setLikeItems(@RequestBody LikedList likedList) {

		return Response.status(Response.Status.OK).entity("Liked").build();
	}

	/**
	 * 
	 * @param libraryitems
	 * @return
	 */
	@RequestMapping(value = "/searchitems", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response searchService(@RequestBody LibraryItems libraryitems) {

		return Response.status(Response.Status.OK).entity("test").build();
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public Response testService() {
		return Response.status(Response.Status.OK).entity("Hello World").build();
	}

	/**
	 * 
	 * @param itemName
	 * @return
	 */
	@RequestMapping(value = "/viewitems", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response viewItems(@RequestParam(value = "itemName") String itemName) {

		List<?> getItems = anonymousUser.searchItems(itemName);
		if (getItems.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND).entity("No requested items available").build();
		} else {
			return Response.status(Response.Status.OK).entity(getItems).build();
		}
	}

}
