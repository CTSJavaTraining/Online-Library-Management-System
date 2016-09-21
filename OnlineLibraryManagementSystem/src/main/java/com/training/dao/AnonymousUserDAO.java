/**
 * 
 */
package com.training.dao;

import java.util.List;
import java.util.Map;

import com.training.entity.LibraryItems;

/**
 * this class holds abstract methods for view and search the library items
 * 
 * @author 447383
 *
 */
public interface AnonymousUserDAO {

	/**
	 * this method is used to display the details of an item based on category
	 * and name
	 * 
	 * @param name
	 * @param itemType
	 * @return
	 */
	public List<LibraryItems> searchItems(String name, String itemType);

	/**
	 * this method returns the map containing library items for view
	 * 
	 * @param maxValue
	 * @return
	 */
	public Map<String, List<LibraryItems>> viewItemsCheck(int maxValue);
}
