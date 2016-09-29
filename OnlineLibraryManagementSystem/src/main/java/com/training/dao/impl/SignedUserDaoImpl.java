package com.training.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.dao.SignedUserDao;
import com.training.entity.LikedList;
import com.training.entity.RatingTable;
import com.training.utils.Utilities;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUserDaoImpl extends AnonymousUserDaoImpl implements SignedUserDao {

	private static final Logger logger = LoggerFactory.getLogger(SignedUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

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

					likedList.setcreatedTime(Utilities.getCurrentDateTime());
				}

				likedList.setmodifiedTime(Utilities.getCurrentDateTime());

				session.saveOrUpdate(likedList);

				logger.info("The item {}", likedList.getId().getItemId(), "liked by user {}",
						likedList.getId().getUserId(), "has been persisted");

				transaction.commit();

				logger.info("The item{}", likedList.getId().getItemId(), "liked by user {} ",
						likedList.getId().getUserId(), "has been commited to DB");

				return 1;
			}

		} catch (Exception e) {
			logger.error("not able to load the liked item details because of DB error {}", e);
			return -1;
		}

		return 0;

	}

	@Override
	public boolean insertRatings(RatingTable ratings) {

		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			logger.info("Session opened to store the ratings for the item {}", ratings.getId().getItemId(),
					"rated by {}", ratings.getId().getUserId());

			boolean checkExistence = findExistance(ratings.getId().getUserId(), ratings.getId().getItemId(),
					"rating_table");

			if (!checkExistence) {
				ratings.setcreatedTime(Utilities.getCurrentDateTime());
			}
			ratings.setmodifiedTime(Utilities.getCurrentDateTime());

			session.saveOrUpdate(ratings);
			logger.info("The ratings for item{}", ratings.getId().getItemId(), "given by{}",
					ratings.getId().getUserId(), "has been persisted");
			transaction.commit();

			logger.info("The details of item{}", ratings.getId().getItemId(), "rated by{}", ratings.getId().getUserId(),
					"has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error("Not able to hit DB to update the rating table {}", e);
		}
		return false;

	}

	@Override
	public boolean findExistance(String userId, String itemId, String tableName) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Query query = session.createQuery("from tableName where itemId = :itemIds and userId= :userId");
			query.setParameter("userId", userId);
			query.setParameter("itemIds", itemId);
			List<?> listResult = query.getResultList();

			if (!(listResult.isEmpty())) {
				logger.info("Hit {}", tableName, "and got info on item {}", itemId, "by {}", userId);
				return true;
			}

		} catch (Exception e) {
			logger.error(" Exception {} Failed to hit the database to check for the rating details of the item {}", e,
					itemId, "rated by user {}", userId);
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

	@Override
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
