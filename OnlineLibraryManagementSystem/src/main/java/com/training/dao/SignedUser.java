package com.training.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.LibraryItems;
import com.training.entity.RatingTable;
import com.training.entity.UserDetails;
import com.training.factory.ApplicationSessionFactory;

/**
 * this class handles the signed user functionalities such as keeping track of
 * likes and ratings and also creating and updating the details of the users
 * 
 * @author 447383
 *
 */
public class SignedUser extends AnonymousUser {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);

	/**
	 * this method is used to make an entry on liked items
	 * 
	 * @param userId
	 * @param itemId
	 * @param likeStatus
	 */
	public void likeItems(String userId, String itemId, int likeStatus) {

		if (findExistanceLikedItems(userId, itemId)) {

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

		if (findExistanceRatings(userId, itemId)) {

			updateRateItems(review, itemId, rating, review);
		} else {
			// insertRateItems(, itemId, rating, review);
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

		Query query;
		String hqlQuery = "from LikedList where itemId = :itemId and userId= :userId";
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
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
			logger.error(e
					+ "Failed to hit the database to check for the existance of the item liked by the same user in DB");
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
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
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
			logger.error(e
					+ "Failed to hit the database to check for the rating details of the item by the same user in DB");
		}

		return false;
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
		Date date = new Date();
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		String hqlQuery = "INSERT INTO LikedList(userId, itemId, likeStatus, createdTime, modifiedTime)"
				+ "select * from LikedList where itemId= :itemId and userId= :userId";
		try (Session session = factory.openSession()) {

			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter(0, userId);
			query.setParameter(1, itemId);
			query.setParameter(2, likeStatus);
			query.setParameter(3, date.toString());
			query.setParameter(4, date.toString());
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the liked status of the item", itemId, " liked by", userId,
						" has not been inserted in liked_list table");
			} else if (result > 0) {
				logger.info("the liked status of the item", itemId, " liked by", userId,
						"has been inserted in liked_list tabl");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item liked details in Database liked_list");
		}
	}

	/**
	 * this method enters user's rating information to rating_table through
	 * 
	 * @param userDetails
	 * @param libraryItems
	 * @param rating
	 * @param review
	 */
	public void insertRateItems(UserDetails userDetails, LibraryItems libraryItems, int rating, String review) {
		Query query;
		Date date = new Date();
		String hqlQuery = "INSERT INTO RatingTable (userId, itemId, rating, review, createdTime, modifiedTime)"
				+ "select * from OldRatingTable where itemId= :itemId and userId= :userId";
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		try (Session session = factory.openSession()) {

			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hqlQuery);
			RatingTable li = new RatingTable();
			li.setUserDetails(userDetails);
			li.setLibraryItems(libraryItems);
			li.setRating(rating);
			li.setReview(review);
			li.setcreatedTime(date);
			li.setmodifiedTime(date);
			query.setParameter("userId", userDetails.getUserId());
			query.setParameter("itemId", libraryItems.getItemId());
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the rating of the item", libraryItems.getItemId(), "rated by", userDetails.getUserId(),
						"has not been inserted in rating_table table");
			} else if (result > 0) {
				logger.info("the rating of the item", libraryItems.getItemId(), "rated by", userDetails.getUserId(),
						" has been inserted in rating_table table");
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
		Date date = new Date();
		String hqlQuery = "UPDATE RatingTable set rating= :rating, review =:review, modifiedTime :mTime where itemId = :itemId and userId= :userId";
		SessionFactory factory = ApplicationSessionFactory.returnFactory();
		try (Session session = factory.openSession()) {

			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			query.setParameter("rating", rating);
			query.setParameter("review", review);
			query.setParameter("mTime", date.toString());
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the ratings of the item", itemId, "rated by", userId,
						"has not been inserted in rating_table table");
			} else if (result > 0) {
				logger.info("the ratings of the item", itemId, "rated by", userId,
						"has been inserted in rating_table table");
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
		Date dateModified = new Date();
		String hqlQuery = "UPDATE LikedList set likeStatus= :likeStatus, modifiedTime :modifiedTime where itemId = :itemId and userId= :userId";
		SessionFactory factory = ApplicationSessionFactory.returnFactory();

		try (Session session = factory.openSession()) {

			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter("userId", userId);
			query.setParameter("itemId", itemId);
			query.setParameter("likeStatus", likeStatus);
			query.setParameter("mTime", dateModified.toString());
			int result = query.executeUpdate();
			if (result == -1) {
				logger.info("the like status of the item", itemId, " liked by", userId,
						"has not been inserted in liked_list table");
			} else if (result > 0) {
				logger.info("the like status of the item", itemId, " liked by", userId,
						"has been inserted in liked_list table");
			}
			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "not able to load the item liked details in Database liked_list");
		}
	}

	public static void main(String args[]) {
		SignedUser su = new SignedUser();
		su.insertLikeItems("122", "122", 4);
		//su.insertRateItems(null, null, 4, "123");
	}
}
