package com.training.restservices;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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

import com.training.dao.LibrarianUserDao;
import com.training.dto.DeleteItemsDto;
import com.training.dto.LibraryItemsDto;
import com.training.utils.LibraryConstants;

/**
 * 
 * @author 447482
 *
 *         RestController class for LibraryServices includes inserting items in
 *         the library,deleting items,adding item format and also checking item
 *         availability
 *
 */
@Component
@RestController
@EnableAutoConfiguration
public class LibraryServices {

	private static final Logger logger = LoggerFactory.getLogger(LibraryServices.class);

	@Autowired
	@Qualifier("librarianUser")
	private LibrarianUserDao librarianUserDao;

	/**
	 * 
	 * @param libraryItems
	 * @return
	 */
	@RequestMapping(value = "/additems", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response addBooks(@RequestBody LibraryItemsDto libraryItemsDto) {

		logger.debug("itemName is {}", libraryItemsDto.getItemName(), " category: {}", libraryItemsDto.getCategory());

		if (!librarianUserDao.itemExistence(libraryItemsDto.getItemName(),
				libraryItemsDto.getItemType().substring(0, 2).toUpperCase())) {
			return Response.status(Response.Status.NOT_IMPLEMENTED)
					.entity("Same item already exists. Kindly update existing items.").build();
		}

		else if (librarianUserDao.addLibraryItems(libraryItemsDto)) {
			return Response.status(Response.Status.OK).entity("Successfully updated item details.").build();
		}

		else {

			logger.error("Error occured when updating Item Name: {}", libraryItemsDto.getItemName());

			return Response.status(Response.Status.BAD_GATEWAY).entity("Item could not be updated. Please try again.")
					.build();

		}
	}

	/**
	 * 
	 * @param deleteItemsDto
	 * @return
	 */
	@RequestMapping(value = "/deleteitems", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response deleteItemAvailable(@RequestBody DeleteItemsDto deleteItemsDto) {

		logger.debug("itemName is {}", deleteItemsDto.getItemName(), " Type: {}", deleteItemsDto.getItemType());

		if (!librarianUserDao.itemExistence(deleteItemsDto.getItemName(),
				deleteItemsDto.getItemType().substring(0, 2).toUpperCase())) {

			if (librarianUserDao.deleteLibraryItems(deleteItemsDto)) {
				return Response.status(Response.Status.OK).entity("Successfully updated item details as available to 0.").build();
			}

			else {
				return Response.status(Response.Status.BAD_GATEWAY)
						.entity("Error occured when deleting item!! Please try again later!!!!").build();
			}
		}

		else {

			logger.error("Error occured when updating Item Name: {}", deleteItemsDto.getItemName());

			return Response.status(Response.Status.NOT_MODIFIED).entity("Item already does not exist.").build();

		}
	}

	/**
	 * 
	 * @param itemID
	 * @return
	 */
	@RequestMapping(value = "/itemavailability", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Produces("application/json")
	public Response checkItemAvailablity(@RequestParam("itemid") String itemID) {

		logger.debug("Checking availability for the item ID : {}", itemID);

		String availabilityStatus = librarianUserDao.checkAvailability(itemID);

		if ((LibraryConstants.EXISTS).equals(availabilityStatus)) {
			return Response.status(Response.Status.OK).entity("Item is available: " + availabilityStatus).build();

		} else if ((LibraryConstants.ERROR).equals(availabilityStatus)) {
			return Response.status(Response.Status.BAD_GATEWAY).entity("Error occured. Please try again").build();
		}

		return Response.status(Response.Status.BAD_REQUEST).entity("Item is not available").build();
	}

}
