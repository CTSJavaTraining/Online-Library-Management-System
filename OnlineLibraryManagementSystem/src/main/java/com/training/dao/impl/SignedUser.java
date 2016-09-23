package com.training.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.dao.SignedUserDAO;
import com.training.entity.LikedList;
import com.training.entity.RatingTable;
import com.training.utils.IDDateGeneratorUtility;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUser extends AnonymousUser implements SignedUserDAO {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * this method is used to insert or update the liked_list table in data base
	 * with the liked list details
	 * 
	 * @param likedList
	 * @return
	 */
	@Override
	public int insertLikedItems(LikedList likedList) {

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			logger.info("Session opened to store the details of item {}", likedList.getId().getItemId(), "liked by {}",
					likedList.getId().getUserId());

			boolean checkLikeStatus = likeExistance(likedList.getId().getUserId(), likedList.getId().getItemId(),
					likedList.getLikeStatus());

			// Check if the user like status and db like status is same. If not
			// same, make requested change.

			if (checkLikeStatus) {

				boolean checkExistence = findExistance(likedList.getId().getUserId(), likedList.getId().getItemId(),
						"LikedList");

				// Check if this is the first like by the user for this item. If
				// not, add modified time.

				if (!checkExistence) {

					likedList.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
				}

				likedList.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());

				session.saveOrUpdate(likedList);

				logger.info("The item {}", likedList.getId().getItemId(), "liked by user {}",
						likedList.getId().getUserId(), "has been persisted");

				transaction.commit();

				logger.info("The item{}", likedList.getId().getItemId(), "liked by user {} ",
						likedList.getId().getUserId(), "has been commited to DB");

				return 1;
			}

		} catch (Exception e) {
			logger.error("not able to load the liked item details because of DB error {}", e.getMessage());
			return -1;
		}

		return 0;

	}

	/**
	 * this method is used to insert or update the rating_table table in data
	 * base with the rating details
	 * 
	 * @param ratings
	 * @return
	 */
	@Override
	public boolean insertRatings(RatingTable ratings) {

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			logger.info("Session opened to store the ratings for the item {}", ratings.getId().getItemId(),
					"rated by {}", ratings.getId().getUserId());

			boolean checkExistence = findExistance(ratings.getId().getUserId(), ratings.getId().getItemId(),
					"rating_table");

			if (!checkExistence) {
				ratings.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
			}
			ratings.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());

			session.saveOrUpdate(ratings);
			logger.info("The ratings for item{}", ratings.getId().getItemId(), "given by{}",
					ratings.getId().getUserId(), "has been persisted");
			transaction.commit();

			logger.info("The details of item{}", ratings.getId().getItemId(), "rated by{}", ratings.getId().getUserId(),
					"has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(), " {} Not able to hit DB to update the rating table");
		}
		return false;

	}

	@Override
	public boolean findExistance(String userId, String itemId, String tableName) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Query query = session.createQuery("from " + tableName + " where itemId = :itemId and userId= :userId");
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (!(listResult.isEmpty())) {
				logger.info("Hit {}", tableName, "and got info on item {}", itemId, "by {}", userId);
				return true;
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),
					" {} Failed to hit the database to check for the rating details of the item {}", itemId,
					"rated by user {}", userId);
		}

		return false;
	}

	@Override
	public boolean likeExistance(String userId, String itemId, int likeStatus) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			Query query = session.createQuery(
					"FROM LikedList where itemId = :itemId AND userId = :userId AND likeStatus = :likeStatus");
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			query.setParameter("likeStatus", likeStatus);

			int returnedLikeStatus = ((Integer) query.getResultList().get(0)).intValue();

			if (returnedLikeStatus == likeStatus) {
				logger.info("No change is commited, since returnedLike status from DB and user input is same: {}",
						returnedLikeStatus);
				return false;

			}

		} catch (Exception e) {
			logger.error("Failed to Query Database due to exception {}", e);
		}

		return true;

	}

	/**
	 * This method is used to update the library_items table in DataBase with
	 * rating details
	 * 
	 * @author 542224
	 * @param itemId
	 * @return
	 */
	public boolean updateRatings(String itemId) {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery(
					"UPDATE LibraryItems set rating=(select avg(rating) from RatingTable where itemId= :itemId");
			query.setParameter("itemId", itemId);

			int rowsAffected = query.executeUpdate();
			logger.debug("rows affected {}", rowsAffected);

			logger.info("The rating for item{}", itemId, " updated successfully in library items table");
			return true;

		} catch (Exception e) {
			logger.error("Failed to query Database due to exception {}", e);
		}
		return false;
	}
}
