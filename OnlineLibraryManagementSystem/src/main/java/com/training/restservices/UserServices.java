package com.training.restservices;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dao.impl.AnonymousUserDaoImpl;
import com.training.dao.impl.UserDaoImpl;
import com.training.dto.UserSignupDto;
import com.training.dto.ViewItemsDto;
import com.training.entity.LoginDetails;
import com.training.response.Response;
import com.training.response.Status;

/**
 * 
 * @author 447482
 *
 *         RestController class for signup services includes inserting user
 *         signup details, user validation, login service and search items from
 *         library
 */

@Component
@RestController
@EnableAutoConfiguration
public class UserServices {

	private static final Logger logger = LoggerFactory.getLogger(UserServices.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	@Qualifier("anonymousUserDaoImpl")
	private AnonymousUserDaoImpl anonymousUserDaoImpl;

	/**
	 * 
	 * @param pageno
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response<Map<String, List<ViewItemsDto>>> getHomePageDetails(
			@RequestParam(value = "pageno", required = false) String pageno) {

		Response<Map<String, List<ViewItemsDto>>> response = new Response<>();
		if (pageno != null) {
			response.setData(anonymousUserDaoImpl.viewItemsCheck(Integer.parseInt(pageno)));

		} else {
			response.setData(anonymousUserDaoImpl.viewItemsCheck(1));
		}
		return response;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response<String> setBasicDetails(@Valid @RequestBody UserSignupDto userSignupDto) {

		String username = userSignupDto.getUserName();

		// If true, user does not exist. So new user is created
		Response<String> response = new Response<>();
		if (userDaoImpl.validateUser(username)) {

			// If true, new user is updated into DB.
			if (userDaoImpl.userSignUp(userSignupDto)) {
				response.setMessage("Sucessfully saved user information");
				response.setStatus(Status.OK);
			} else {
				response.setMessage("Error creating user. Please try again later!");
				response.setStatus(Status.EXPECTATION_FAILED);
			}

		} else {
			response.setMessage("User already exists!");
		}
		return response;
	}

	/**
	 * 
	 * @param loginDetails
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response<String> validateLoginDetails(@RequestBody LoginDetails loginDetails) {

		logger.info("Validating user for logging in {},{}", loginDetails.getUserId(), loginDetails.getPassword());

		String userId = loginDetails.getUserId();

		String password = loginDetails.getPassword();
		Response<String> response = new Response<>();
		if ((!userId.isEmpty()) && (!password.isEmpty())) {

			if (userDaoImpl.validateLoginUser(userId)) {
				response.setMessage("User does not exist. Please signup");
			} else {

				if (userDaoImpl.validateLogin(userId, password)) {
					response.setMessage("User " + userId + " logged in successfully");
				} else {
					response.setMessage("Incorrect Login Details");
				}
			}
		} else {
			response.setMessage("User name and password should not be empty");
		}
		return response;
	}

	/**
	 * 
	 * @param userSignupDto
	 * @return
	 */
	@RequestMapping(value = "/uservalidation", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response<String> userNameExistance(@RequestBody UserSignupDto userSignupDto) {

		String username = userSignupDto.getUserName();

		Response<String> response = new Response<>();
		if (!username.isEmpty()) {

			if (userDaoImpl.validateUser(username)) {
				response.setMessage("User does not exist!!!");
			} else {
				response.setMessage("User exist");
			}
		}

		else {
			response.setMessage("User name should not be blank");
		}
		return response;
	}

	/**
	 * 
	 * @param itemName
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = "/searchitems", method = RequestMethod.GET)
	@ResponseBody
	@Produces("application/json")
	public Response<List<ViewItemsDto>> searchService(@RequestParam("itemname") String itemName,
			@RequestParam(value = "pageno", required = false) String pageNo) {

		Response<List<ViewItemsDto>> response = new Response<>();

		List<ViewItemsDto> viewItemsDtoList;

		if (pageNo != null) {
			viewItemsDtoList = anonymousUserDaoImpl.searchItems(itemName, Integer.parseInt(pageNo));
		} else {
			viewItemsDtoList = anonymousUserDaoImpl.searchItems(itemName, 1);
		}

		if (!viewItemsDtoList.isEmpty()) {
			response.setData(viewItemsDtoList);
		} else {
			response.setMessage("Requested item does not exist");
		}
		return response;
	}

	/**
	 * 
	 * @return
	 */
	@Path("test")
	@GET
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public Response<String> testService() {
		Response<String> response = new Response<>();
		System.out.println("Calling inside userservices");
		response.setMessage("Hello World!! Test");

		return response;
	}

}
