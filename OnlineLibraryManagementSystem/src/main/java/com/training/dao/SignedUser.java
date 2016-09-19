package com.training.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.factory.ApplicationSessionFactory;
import com.training.utils.LocalDateTimeUtils;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUser extends AnonymousUser {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);
	LocalDateTimeUtils dateTimeUtils = new LocalDateTimeUtils();

	/**
	 * this method is used to make an entry on liked items
	 * 
	 * @param userId
	 * @param itemId
	 * @param likeStatus
	 */
	public void likeItems(String userId, String itemId, int likeStatus) {

		if (findExistanceLikedItems(userId, itemId) == true) {

			updateLikeItems(userId, itemId, likeStatus);

		} else {
			insertLikeItems(userId, itemId, likeStatus);
		}

	}

	/**
	 * this method is used to make an entry on rate items
	 * 
	 * @param userId
	 * @param itemId
	 * @param rating
	 * @param review
	 */
	public void rateItems(String userId, String itemId, int rating, String review) {

		if (findExistanceRatings(userId, itemId) == true) {

			updateRateItems(review, itemId, rating, review);
		} else {
			insertRateItems(userId, itemId, rating, review);
		}

	}

	/**
	 * this method checks if rating or review exists for a particular item by
	 * the same user and returns true if exists else returns false
	 * 
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public boolean findExistanceLikedItems(String userId, String itemId) {
		boolean status = false;
		Query query;
		String hqlQuery = "from LikedList where item_id = :itemId and user_id= :userId";
		try {

			SessionFactory factory = ApplicationSessionFactory.returnFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			query = session.createQuery(hqlQuery);
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (listResult == null) {

				status = false;
			} else {
				status = true;

			}
		} catch (Exception e) {
			logger.error(e
					+ "Failed to hit the database to check for the existance of the item liked by the same user in DB");
		}

		return status;
	}

	/**
	 * this method checks if rating or review exists for a particular item by
	 * the same user and returns true if exists else returns false
	 * 
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public boolean findExistanceRatings(String userId, String itemId) {
		boolean status = true;
		Query query;
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hqlQuery = "from RatingTable where item_id = :item_id and user_id= :user_id";
			query = session.createQuery(hqlQuery);
			query.setParameter("user_id", userId);
			query.setParameter("item_id", itemId);
			List<?> listResult = query.getResultList();
			transaction.commit();
			if (listResult == null) {

				status = false;
			} else {
				status = true;

			}
		} catch (Exception e) {
			logger.error(e
					+ "Failed to hit the database to check for the rating details of the item by the same user in DB");
		}

		return true;
	}

	/**
	 * this method inserts the liked item details into liked_list table in DB
	 * 
	 * @param userId
	 * @param itemId
	 * @param likeStatus
	 */
	public void insertLikeItems(String userId, String itemId, int likeStatus) {
		Query query;
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hqlQuery = "INSERT INTO LikedList(user_id, item_id, like_status, c_time, m_time)";
			query = session.createQuery(hqlQuery);
			query.setParameter("user_id", userId);
			query.setParameter("item_id", itemId);
			query.setParameter("like_status", likeStatus);
			query.setParameter("c_time", dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime())));
			query.setParameter("m_time", dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime())));
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the details of the item liked has not been inserted in liked_list table");
			} else if (result > 0) {
				logger.info("the details of the item liked has been inserted in liked_list tabl");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item liked details in Database liked_list");
		}
	}

	/**
	 * this method enters user's rating information to rating_table through
	 * 
	 * @param userId
	 * @param itemId
	 * @param rating
	 * @param review
	 */
	public void insertRateItems(String userId, String itemId, int rating, String review) {
		Query query;
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String hqlQuery = "INSERT INTO RatingTable(userId, itemId, rating, review, createdTime, modifiedTime)";
			query = session.createQuery(hqlQuery);
			query.setParameter("user_id", userId);
			query.setParameter("item_id", itemId);
			query.setParameter("rating", rating);
			query.setParameter("review", review);
			query.setParameter("c_time", dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime())));
			query.setParameter("m_time", dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime())));
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the details of the item rated has not been inserted in rating_table table");
			} else if (result > 0) {
				logger.info("the details of the item rated has been inserted in rating_table table");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item rated details in Database rating_table");
		}
	}

	/**
	 * 
	 * @param userId
	 * @param itemId
	 * @param rating
	 * @param review
	 */
	public void updateRateItems(String userId, String itemId, int rating, String review) {
		Query query;
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Timestamp dateModified = dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime()));
		try {
			String hqlQuery = "UPDATE RatingTable set rating= :rating, review =:review, m_time :m_time where item_id = :item_id and user_id= :user_id";
			query = session.createQuery(hqlQuery);
			query.setParameter("user_id", userId);
			query.setParameter("item_id", itemId);
			query.setParameter("rating", rating);
			query.setParameter("review", review);
			query.setParameter("m_time", dateModified);
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the details of the item rated has not been inserted in rating_table table");
			} else if (result > 0) {
				logger.info("the details of the item rated has been inserted in rating_table table");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item rated details in Database rating_table");
		}
	}

	/**
	 * 
	 * @param userId
	 * @param itemId
	 * @param likeStatus
	 */
	public void updateLikeItems(String userId, String itemId, int likeStatus) {
		Query query;
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Timestamp dateModified = dateTimeUtils.convertToDatabaseColumn((dateTimeUtils.getLocalDateTime()));
		try {
			String hqlQuery = "UPDATE LikedList set like_status= :like_status, m_time :m_time where item_id = :item_id and user_id= :user_id";
			query = session.createQuery(hqlQuery);
			query.setParameter("user_id", userId);
			query.setParameter("item_id", itemId);
			query.setParameter("like_status", likeStatus);
			query.setParameter("m_time", dateModified);
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the details of the item liked has not been inserted in liked_list table");
			} else if (result > 0) {
				logger.info("the details of the item liked has been inserted in liked_list tabl");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item liked details in Database liked_list");
		}
	}

	public static void main(String args[]) {
		SignedUser su = new SignedUser();
		su.rateItems("123", "123", 4, "null");
		su.likeItems("123", "123", 4);
	}
}
