package com.training.dao;

import java.util.List;
import java.util.Map;

import com.training.blayer.ViewItemsDto;

/**
 * this interface holds abstract methods for view and search the library items
 * 
 * @author 447383
 *
 */
public interface AnonymousUserDao {

	/**
	 * this method returns the map containing library items for view
	 * 
	 * @param maxValue
	 * @return
	 */
	public Map<String, List<ViewItemsDto>> viewItemsCheck(int maxValue);

	/**
	 * this method is used to display the details of an item based on category
	 * and name
	 * 
	 * @param name
	 * @param pageNo2
	 * @return
	 */
	public List<ViewItemsDto> searchItems(String name, int pageNo);

	/**
	 * 
	 * @param category
	 * @param startResult
	 * @return
	 */
	public List<ViewItemsDto> runQuery(String category, int startResult);
}
