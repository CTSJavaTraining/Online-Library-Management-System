package com.training.restservices;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.blayer.UserSignupDTO;
import com.training.dao.impl.UserDAOImpl;
import com.training.entity.LibraryItems;
import com.training.entity.LikedList;
import com.training.entity.LoginDetails;

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
	private UserDAOImpl userDaoImpl;

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response setBasicDetails(@RequestBody UserSignupDTO userSignupDto) {

		String username = userSignupDto.getUserName();

		boolean userValidationStatus = userDaoImpl.validateUser(username);

		// If true, user does not exist. So new user is created
		if (userValidationStatus) {
			System.out.println("---------------True user does not exist");
			boolean signupStatus = userDaoImpl.userSignUp(userSignupDto);

			// If true, new user is updated into DB.
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
	public Response userNameExistance(@RequestBody UserSignupDTO userSignupDto) {

		String username = userSignupDto.getUserName();

		if (!username.isEmpty()) {

			if (userDaoImpl.validateUser(username)) {
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

		logger.info("Validating user for logging in {},{}", loginDetails.getUserId(), loginDetails.getPassword());

		String userId = loginDetails.getUserId();

		String password = loginDetails.getPassword();

		if ((!userId.isEmpty()) && (!password.isEmpty())) {

			if (userDaoImpl.validateLoginUser(userId)) {
				return Response.status(Response.Status.NOT_FOUND).entity("User does not exist. Please signup").build();
			} else {

				if (userDaoImpl.validateLogin(userId, password)) {
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
	/*
	 * @RequestMapping(value = "/viewitems", method = RequestMethod.GET,
	 * consumes = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody
	 * 
	 * @Produces("application/json") private Response
	 * viewItems(@RequestParam(value = "itemName") String itemName) {
	 * 
	 * List<?> getItems = anonymousUser.searchItems(itemName); if
	 * (getItems.isEmpty()) { return Response.status(Response.Status.NOT_FOUND).
	 * entity("No requested items available").build(); } else { return
	 * Response.status(Response.Status.OK).entity(getItems).build(); } }
	 */

}
