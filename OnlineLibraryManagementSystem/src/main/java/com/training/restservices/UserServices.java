package com.training.restservices;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.blayer.UserSignupDto;
import com.training.blayer.ViewItemsDto;
import com.training.dao.impl.AnonymousUserDaoImpl;
import com.training.dao.impl.UserDaoImpl;
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
	private UserDaoImpl userDaoImpl;

	@Autowired
	@Qualifier("anonymousUserDaoImpl")
	private AnonymousUserDaoImpl anonymousUserDaoImpl;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<ViewItemsDto>> getHomePageDetails(
			@RequestParam(value = "pageno", required = false) String pageno) {

		if (pageno != null) {
			return anonymousUserDaoImpl.viewItemsCheck(Integer.parseInt(pageno));

		} else {
			return anonymousUserDaoImpl.viewItemsCheck(1);
		}

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response setBasicDetails(@Valid @RequestBody UserSignupDto userSignupDto) {

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
	 * @param userSignupDto
	 * @return
	 */
	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response userNameExistance(@RequestBody UserSignupDto userSignupDto) {

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
	public Response validateLoginDetails(@RequestBody LoginDetails loginDetails) {

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
	public Response setLikeItems() {

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
	public Response searchService(@RequestParam("itemname") String itemName, @RequestParam("pageno") int pageNo) {

		List<ViewItemsDto> viewItemsDtoList = anonymousUserDaoImpl.searchItems(itemName, pageNo);

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
