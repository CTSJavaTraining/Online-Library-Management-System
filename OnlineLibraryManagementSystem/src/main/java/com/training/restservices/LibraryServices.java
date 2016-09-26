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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.blayer.BooksDTO;
import com.training.blayer.MoviesDTO;
import com.training.blayer.MusicDTO;
import com.training.dao.impl.LibrarianUser;

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
	private LibrarianUser librarianUser;

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addbook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addBooks(@RequestBody BooksDTO booksDto) {

		logger.debug("itemName is {}" + booksDto.getItemName());

		logger.info("Checking for existence of book");
		logger.info("Item type is: {}",booksDto.getItemType());

		if (!librarianUser.itemExistence(booksDto.getItemName(), booksDto.getItemType().substring(0, 2).toUpperCase())) {
			return Response.status(Response.Status.NOT_IMPLEMENTED)
					.entity("Same item already exists. Kindly update existing items.").build();
		} else if (librarianUser.addBooks(booksDto)) {
			return Response.status(Response.Status.OK).entity("Successfully updated book details.").build();
		} else {

			logger.error("Error occured when updating Book Name: {}", booksDto.getItemName());

			return Response.status(Response.Status.BAD_GATEWAY).entity("Item could not be updated. Please try again.")
					.build();

		}
	}

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addmusic", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMusic(@RequestBody MusicDTO musicDto) {

		if (librarianUser.itemExistence(musicDto.getItemName(), musicDto.getItemType().substring(0, 2))) {
			return Response.status(Response.Status.NOT_IMPLEMENTED)
					.entity("Same item already exists. Kindly update existing items.").build();
		} else if (librarianUser.addMusic(musicDto)) {
			return Response.status(Response.Status.OK).entity("Successfully updated book details.").build();
		} else {

			logger.error("Error occured when updating music Name: {}", musicDto.getItemName());

			return Response.status(Response.Status.BAD_GATEWAY).entity("Item could not be updated. Please try again.")
					.build();

		}

	}

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/addmovies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response addMovies(@RequestBody MoviesDTO moviesDto) {

		return Response.status(Response.Status.OK).entity("Successfully updated movie details.").build();

	}

	/*	*//**
			 * 
			 * @param libraryItems
			 * @return
			 *//*
			 * @RequestMapping(value = "/additemformat", method =
			 * RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
			 * 
			 * @ResponseBody
			 * 
			 * @Produces("application/json") private Response
			 * addItemFormat(@RequestBody LibraryItems libraryItems) {
			 * 
			 * return Response.status(Response.Status.OK).
			 * entity("Successfully updated item format.").build();
			 * 
			 * }
			 * 
			 * @RequestMapping(value = "/itemavailability", method =
			 * RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
			 * 
			 * @ResponseBody
			 * 
			 * @Produces("application/json") private Response
			 * checkItemAvailablity(@RequestParam("itemID") String itemID) {
			 * 
			 * logger.debug("Checking availability for the item ID : {}",
			 * itemID);
			 * 
			 * String availabilityStatus =
			 * librarianUser.checkAvailability(itemID);
			 * 
			 * if (("exist").equals(availabilityStatus)) { return
			 * Response.status(Response.Status.OK).entity("Item is available: "
			 * + availabilityStatus).build();
			 * 
			 * } else if ("Error".equals(availabilityStatus)) { return
			 * Response.status(Response.Status.BAD_GATEWAY).
			 * entity("Error occured. Please try again").build(); }
			 * 
			 * return Response.status(Response.Status.BAD_REQUEST).
			 * entity("Item is not available").build(); }
			 */
}
