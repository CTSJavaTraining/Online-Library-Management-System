package com.training.restservices;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	private void registerUser() {
		
	}

}
