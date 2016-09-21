package com.training.daoimplementation;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.dao.SignedUserDAO;
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
public class SignedUser extends AnonymousUser implements SignedUserDAO {

	private static final Logger logger = LoggerFactory.getLogger(SignedUser.class);
	private SessionFactory factory = UtilitiesFactory.returnFactory();

	/**
	 * this method is used to insert or update the liked_list table in data base
	 * with the liked list details
	 * 
	 * @param likedList
	 * @return
	 */
	@Override
	public boolean insertLikedItems(LikedList likedList) {

		try (Session session = factory.openSession()) {

			Transaction transaction = session.beginTransaction();

			logger.info("Session opened to store the details of item {}", likedList.getId().getItemId(), "liked by {}",
					likedList.getId().getUserId());

			boolean checkExistence = findExistance(likedList.getId().getUserId(), likedList.getId().getItemId(),
					"liked_list");

			if (!checkExistence) {

				likedList.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
			}
			likedList.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());

			session.saveOrUpdate(likedList);

			logger.info("The item {}", likedList.getId().getItemId(), "liked by user {}", likedList.getId().getUserId(),
					"has been persisted");

			transaction.commit();

			logger.info("The item{}", likedList.getId().getItemId(), "liked by user {} ", likedList.getId().getUserId(),
					"has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error("not able to load the liked item details because of DB error {}", e.getMessage());
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
	@Override
	public boolean insertRatings(RatingTable ratings) {

		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			logger.info("Session opened to store the ratings for the item {}", ratings.getId().getItemId(),
					"rated by {}", ratings.getId().getUserId());
			boolean checkExistence = findExistance(ratings.getId().getUserId(), ratings.getId().getItemId(),
					"rating_table");

			if (!checkExistence) {
				ratings.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
			}
			ratings.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());

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
		Query query;
		String hqlQuery = "from " + tableName + " where itemId = :itemId and userId= :userId";
		try (Session session = factory.openSession()) {

			session.beginTransaction();
			query = session.createQuery(hqlQuery);
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

}
