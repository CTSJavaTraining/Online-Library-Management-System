package com.training.restservices;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.LoginDetails;
import com.training.entity.UserContactDetails;
import com.training.entity.UserDetails;

/**
 * 
 * @author 447482 Sign up service to validate and insert NEW USER data into
 *         database
 */
@ComponentScan
@RestController
@EnableAutoConfiguration
@RequestMapping("/signup")
public class SignupService {

	@RequestMapping(name = "new user details", value = "/newuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setBasicDetails(@RequestBody UserDetails userdetails) {

		if (!userdetails.getUserName().trim().isEmpty()) {

			return "Success userbasic details";
		}

		else {

			return "Username should not be empty";
		}

	}

	@RequestMapping(name = "new user contact details", value = "/newuser/contact", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setContactDetails(@RequestBody UserContactDetails contactDetails) {

		if (!contactDetails.getEmailId().trim().isEmpty()) {

			return "Success user contact details";
		}

		else {

			return "Email id should not be empty";
		}

	}

	@RequestMapping(name = "new user contact details", value = "/newuser/logindetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setLoginPassword(@RequestBody LoginDetails loginDetails) {

		if (!loginDetails.getPassword().trim().isEmpty()) {

			return "Success password type";
		}

		else {

			return "Password should not be empty";
		}

	}

}
