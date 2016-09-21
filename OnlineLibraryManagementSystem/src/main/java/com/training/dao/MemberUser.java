package com.training.dao;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.WishList;
import com.training.factory.UtilitiesFactory;

/**
 * this method holds the subscribe and add wish list by teh user
 * 
 * @author 447383
 *
 */
public class MemberUser {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);
	private SessionFactory factory = UtilitiesFactory.returnFactory();
	Date date = new Date();

	/**
	 * this method is used to add or update wish list
	 * 
	 * @param wishList
	 * @return
	 */
	public boolean addUpdateWishList(WishList wishList) {

		return false;
	}

	/**
	 * this method is used to check for existence of records of the user on the
	 * same item
	 * 
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public boolean findExistenceWishList(String userId, String itemId) {

		return false;
	}
}
