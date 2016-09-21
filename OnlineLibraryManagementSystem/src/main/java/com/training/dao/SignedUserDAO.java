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
	 * 
	 * @param likedList
	 * @return
	 */
	public boolean insertLikedItems(LikedList likedList);
	
	/**
	 * 
	 * @param ratings
	 * @return
	 */
	public boolean insertRatings(RatingTable ratings);
	

}
