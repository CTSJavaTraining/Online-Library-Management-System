package com.training.restservices;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.blayer.UserSignupDTO;
import com.training.blayer.ViewItemsDto;
import com.training.dao.impl.AnonymousUser;
import com.training.dao.impl.UserDAOImpl;
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

	@Autowired
	@Qualifier("anonymousUser")
	private AnonymousUser anonymousUser;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	@Produces("application/json")
	private Response getHomePageDetails() {

		Map<String, List<ViewItemsDto>> viewMap = anonymousUser.viewItemsCheck(1);


		String mapAsJson = null;
		try {
			mapAsJson = new ObjectMapper().writeValueAsString(viewMap);
		} catch (JsonProcessingException e) {
			logger.error("Json exception: {}", e);
		}

		return Response.status(Response.Status.OK).entity(mapAsJson).build();
	}

	@RequestMapping(value = "/home&{pageno}", method = RequestMethod.GET)
	@ResponseBody
	@Produces("application/json")
	private Response navigateHomePageDetails(@PathVariable("pageno") int pageno) {
		return null;

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response setBasicDetails(@RequestBody UserSignupDTO userSignupDto) {

		String username = userSignupDto.getUserName();

		// If true, user does not exist. So new user is created
		if (userDaoImpl.validateUser(username)) {

			// If true, new user is updated into DB.
			if (userDaoImpl.userSignUp(userSignupDto)) {
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
	@RequestMapping(value = "/searchitems", method = RequestMethod.GET)
	@ResponseBody
	@Produces("application/json")
	private Response searchService(@RequestParam("itemname") String itemName, @RequestParam("pageno") int pageNo) {

		List<ViewItemsDto> viewItemsDtoList = anonymousUser.searchItems(itemName, pageNo);

		if (!viewItemsDtoList.isEmpty()) {
			return Response.status(Response.Status.OK).entity(viewItemsDtoList).build();
		} else {
			return Response.status(Response.Status.OK).entity("No items available of name:" + itemName).build();
		}
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public Response testService() {
		return Response.status(Response.Status.OK).entity("Hello World").build();
	}

}
