package com.training.restservices;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.dao.LibrarianDAO;
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
	
	@RequestMapping(value = "/searchitems", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	private Response viewItems(@RequestParam(value="itemName") String itemName) {
		
		LibrarianDAO libraryDAO=new LibrarianDAO();
		List<?> getItems=libraryDAO.searchItems(itemName);
		
		if(getItems.isEmpty()){
			return Response.status(Response.Status.NOT_FOUND).entity("No requested items available").build();
		}
		else{
			return Response.status(Response.Status.OK).entity(getItems).build();
		}
	}

}
