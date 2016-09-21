/**
 * 
 */
package com.training.dao;

import com.training.entity.SubscribedList;
import com.training.entity.WishList;

/**
 * @author 447383
 *
 */
public interface MemberUserDAO {

	/**
	 * this method is used to insert or update the wish list details of teh user
	 * 
	 * @param wishList
	 * @return
	 */
	public boolean insertWishList(WishList wishList);

	/**
	 * This method is used to insert or update the subscribed list of the user
	 * 
	 * @param subscribedList
	 * @return
	 */
	public boolean insertSubscribedList(SubscribedList subscribedList);

	/**
	 * this method is used to find the entry in the table
	 * 
	 * @param memberId
	 * @param itemId
	 * @param tableName
	 * @return
	 */
	boolean findExistance(int memberId, String itemId, String tableName);
}
