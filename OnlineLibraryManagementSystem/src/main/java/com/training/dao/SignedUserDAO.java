/**
 * 
 */
package com.training.dao;

import com.training.entity.LikedList;
import com.training.entity.RatingTable;

/**
 * @author 447383
 *
 */
public interface SignedUserDAO {

	/**
	 * this method is used to insert details into liked list table
	 * 
	 * @param likedList
	 * @return
	 */
	public boolean insertLikedItems(LikedList likedList);

	/**
	 * this method is used to insert details into ratings table
	 * 
	 * @param ratings
	 * @return
	 */
	public boolean insertRatings(RatingTable ratings);

	/**
	 * this method is used to find the entry in the table
	 * 
	 * @param userId
	 * @param itemId
	 * @param tableName
	 * @return
	 */
	public boolean findExistance(String userId, String itemId, String tableName);

}
