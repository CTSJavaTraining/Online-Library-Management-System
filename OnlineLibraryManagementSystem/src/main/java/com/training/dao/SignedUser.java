package com.training.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.LikedList;
import com.training.entity.RatingTable;
import com.training.factory.UtilitiesFactory;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUser extends AnonymousUser {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);
	SessionFactory factory = UtilitiesFactory.returnFactory();
	Date date = new Date();

	/**
	 * this method is used to insert or update the liked_list table in data base
	 * with the liked list details
	 * 
	 * @param likedList
	 * @return
	 */
	public boolean insertLikedItems(LikedList likedList) {

		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();

			logger.info("Session opened to store the details of item", likedList.getId().getItemId(), "liked by",
					likedList.getId().getUserId());

			boolean checkExistence = findExistanceLikedItems(likedList.getId().getUserId(),
					likedList.getId().getItemId());
			if (likedList.getId() != null) {
				if ((!checkExistence)) {

					likedList.setcreatedTime(date);
				}
				likedList.setmodifiedTime(date);
			}
			session.saveOrUpdate(likedList);

			logger.info("The details of item", likedList.getId().getItemId(), "liked by user ",
					likedList.getId().getUserId(), "has been persisted");

			transaction.commit();

			logger.info("The details of item", likedList.getId().getItemId(), "liked by user ",
					likedList.getId().getUserId(), "has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error("not able to load the liked item details because of DB error", e.getMessage());
		}
		return false;

	}

	/**
	 * this method is used to insert or update the rating_table table in data
	 * base with the rating details
	 * 
	 * @param ratings
	 * @return
	 */
	public boolean insertRatings(RatingTable ratings) {

		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			logger.info("Session opened to store the ratings for the item", ratings.getId().getItemId(), "rated by",
					ratings.getId().getUserId());
			boolean checkExistence = findExistanceRatings(ratings.getId().getUserId(), ratings.getId().getItemId());
			if (ratings.getId() != null) {
				if (!checkExistence) {
					ratings.setcreatedTime(date);
				}
				ratings.setmodifiedTime(date);
			}
			session.saveOrUpdate(ratings);
			logger.info("The ratings for item", ratings.getId().getItemId(), "given by", ratings.getId().getUserId(),
					"has been persisted");
			transaction.commit();
			logger.info("The details of item", ratings.getId().getItemId(), "rated by", ratings.getId().getUserId(),
					"has been commited to DB");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), "Not able to hit DB to update the rating table");
		}
		return false;

	}

	/**
	 * this method returns true if there is an existing entry in database for
	 * the same item and user
	 * 
	 * @param userId
	 * @param itemId
	 * @return
	 */
	public boolean findExistanceLikedItems(String userId, String itemId) {

		Query query;
		String hqlQuery = "from LikedList where itemId = :itemId and userId= :userId";
		try (Session session = factory.openSession()) {

			session.beginTransaction();

			query = session.createQuery(hqlQuery);
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (!(listResult.isEmpty())) {
				logger.info("Hit LikedList table");
				return true;

			}

		} catch (Exception e) {
			logger.error(e + "Failed to hit the database to check for the existance of the item", itemId, "liked by",
					userId);
		}

		return false;
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

		Query query;
		String hqlQuery = "from RatingTable where itemId = :itemId and userId= :userId";
		try (Session session = factory.openSession()) {

			session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (!(listResult.isEmpty())) {
				logger.info("Hit Rating table");
				return true;
			}

		} catch (Exception e) {
			logger.error(e + "Failed to hit the database to check for the rating details of the item", itemId,
					"rated by user", userId);
		}

		return false;
	}
}
