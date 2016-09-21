package com.training.restservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.entity.LibraryItems;

@ComponentScan
@RestController
@EnableAutoConfiguration
public class LibraryServices {

	@RequestMapping(value = "/addbook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addBooks(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated book details.").build();

	}

	@RequestMapping(value = "/addmusic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMusic(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated music details.").build();

	}

	@RequestMapping(value = "/addmovies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMovies(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated movie details.").build();

	}

	@RequestMapping(value = "/additemformat", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addItemFormat(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated item format.").build();

	}

}
