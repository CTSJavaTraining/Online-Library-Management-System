package com.training.restservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.blayer.MoviesDTO;
import com.training.dao.impl.LibrarianDAO;
import com.training.entity.LibraryItems;

/**
 * 
 * @author 447482
 *
 */
@Component
@RestController
@EnableAutoConfiguration
public class LibraryServices {

	private static final Logger logger = LoggerFactory.getLogger(LibraryServices.class);

	@Autowired
	private LibrarianDAO librarianDAO;

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addbook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addBooks(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated book details.").build();

	}

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addmusic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMusic(@RequestBody MoviesDTO moviesDTO) {

		return Response.status(Response.Status.OK).entity("Successfully updated music details.").build();

	}

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addmovies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMovies(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated movie details.").build();

	}

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/additemformat", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addItemFormat(@RequestBody LibraryItems libraryItems) {

		return Response.status(Response.Status.OK).entity("Successfully updated item format.").build();

	}

	@RequestMapping(value = "/itemavailability", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response checkItemAvailablity(@RequestParam("itemID") String itemID) {

		logger.debug("Checking availability for the item ID : {}", itemID);

		String availabilityStatus = librarianDAO.checkAvailability(itemID);

		if (!availabilityStatus.isEmpty()) {
			return Response.status(Response.Status.OK).entity("Item is available: " + availabilityStatus).build();

		} else if ("Error".equals(availabilityStatus)) {
			return Response.status(Response.Status.BAD_GATEWAY).entity("Error occured. Please try again").build();
		}

		return Response.status(Response.Status.BAD_REQUEST).entity("Item is not available").build();
	}

}
