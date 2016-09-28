package com.training.dao;

import com.training.blayer.DeleteItemsDTO;
import com.training.blayer.LibraryItemsDTO;

/**
 * interface holds abstract methods for class Librarian in dao.impl package
 * 
 * @author 447383
 *
 */

public interface LibrarianDAO {

	/**
	 * To check availability of the item
	 * 
	 * @param itemId
	 *            It is used to query ItemFormat table for checking the
	 *            availability.
	 * @return true if available else return false
	 */
	public String checkAvailability(String itemId);

	/**
	 * this method is used to check if the item already exists to decide if to
	 * add or update a library item
	 * 
	 * @param itemName
	 * @param shortItemType
	 * @return
	 */
	public boolean itemExistence(String itemName, String shortItemType);

	/**
	 * This method is used by service to add new library items into database.
	 * 
	 * @param libraryItemsDto
	 *            to get the item details from user in jSON and validate them
	 *            and update in DB
	 * @return
	 */
	public boolean addLibraryItems(LibraryItemsDTO libraryItemsDto);

	/**
	 * 
	 * @param deleteItemsDto
	 *            gets itemname, itemtype and performs updating available column
	 *            in item format table
	 * @return boolean if the item deletion is updated or not
	 */
	public boolean deleteLibraryItems(DeleteItemsDTO deleteItemsDto);
}
