package com.training.daoimplementation;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
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
	 * @param tableName
	 * @return
	 */
	public boolean findExistenceWishList(String userId, String itemId, String tableName) {
		Query query;
		String hqlQuery = "from " + tableName + " where itemId= :itemId and userId= :userId";
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			logger.info("Session has been created to update wishlist details for item {} ", itemId, "for user {}",
					userId);
			query = session.createQuery(hqlQuery);
			List<?> resultList = query.getResultList();
			if (!(resultList.isEmpty())) {
				logger.info("Wish List table has been hit and got the information");
				return true;

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), " the details of item {} ", itemId,
					" was not been added to the wishlist table submitted by user {} ", userId);
		}
		return false;
	}
}
