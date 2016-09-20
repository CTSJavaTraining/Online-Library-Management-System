package com.training.restservices;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hibernate.Session;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.AddressDetails;
import com.training.entity.LibraryItems;
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

	SessionFactory factory = new Configuration().configure().buildSessionFactory();

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private String setBasicDetails(@RequestBody UserDetails userdetails) {

		
		return "Successfully saved your information";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response loginService(@RequestBody UserDetails userdetails) {

		return Response.status(Response.Status.OK).entity(new UserDetails()).build();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	private Response userNameExistance(@RequestParam("username") String username) {

		logger.info("welcome");
		return Response.status(Response.Status.OK).entity("user namae exists").build();

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
