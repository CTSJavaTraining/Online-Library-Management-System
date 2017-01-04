package com.training.restservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

/**
 * 
 * @author VICKY BRB
 *
 */
@Component
public class Service {

	private static final String HELLO = "hello";
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("check")
	@Produces(MediaType.TEXT_PLAIN)
	public String check() {
		return HELLO;
	}
}
