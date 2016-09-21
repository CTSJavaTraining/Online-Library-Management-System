/**
 * 
 */
package com.training.dao;

import com.training.entity.LikedList;

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
	
	
}
