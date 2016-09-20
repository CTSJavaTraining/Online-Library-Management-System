package com.training.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.LikedList;
import com.training.entity.RatingTable;
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
	SessionFactory factory = ApplicationSessionFactory.returnFactory();
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
			session.beginTransaction();
			logger.info("Session Opened to insert or update the Liked list details");
			if (likedList.getId() != null) {
				likedList.setcreatedTime(date);
				likedList.setmodifiedTime(date);
			}
			session.saveOrUpdate(likedList);
			logger.info("the details about the liked items are used ");
			session.getTransaction().commit();

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
			session.beginTransaction();
			if (ratings.getId() != null) {
				ratings.setcreatedTime(date);
				ratings.setmodifiedTime(date);
			}
			session.saveOrUpdate(ratings);
			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			logger.error(e.getMessage(),"Not able to hit DB to update the rating table");
		}
		return false;

	}

}
