package com.training.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.dao.MemberUserDao;
import com.training.entity.SubscribedList;
import com.training.entity.WishList;
import com.training.utils.Utilities;

/**
 * this method holds the subscribe and add wish list by teh user
 * 
 * @author 447383
 *
 */
public class MemberUserDaoImpl extends AnonymousUserDaoImpl implements MemberUserDao {

	private static final Logger logger = LoggerFactory.getLogger(SignedUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean findExistance(int memberId, String itemId, String tableName) {
		Query query;
		String hqlQuery = "from " + tableName + " where itemId = :itemId and memberID= :userId";
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter("userId", memberId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (!(listResult.isEmpty())) {
				logger.info("Hit {}", tableName, "and got info on item {}", itemId, "by {}", memberId);
				return true;
			}

		} catch (Exception e) {
			logger.error(" Failed to hit the database to check for the rating details of the item {}, {}", itemId, e);
		}

		return false;
	}

	@Override
	public boolean insertWishList(WishList wishList) {

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();
			logger.info("Session opened to store the wishList for the item {}", wishList.getId().getItemId(), " by {}",
					wishList.getId().getMemberId());

			boolean checkExistence = findExistance(wishList.getId().getMemberId(), wishList.getId().getItemId(),
					"rating_table");

			if (!checkExistence) {
				wishList.setcreatedTime(Utilities.getCurrentDateTime());
			}
			wishList.setmodifiedTime(Utilities.getCurrentDateTime());

			session.saveOrUpdate(wishList);
			logger.info("The wishList updated for item{}", wishList.getId().getItemId(), " by{}",
					wishList.getId().getMemberId(), "has been persisted");

			transaction.commit();
			logger.info("The details of item{}", wishList.getId().getItemId(),
					" wished by{} is stored in wish list table", wishList.getId().getMemberId(),
					"has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error(" Not able to hit Wish List table {}", e);
		}
		return false;

	}

	@Override
	public boolean insertSubscribedList(SubscribedList subscribedList) {

		try (Session session = sessionFactory.openSession()) {

			Transaction transaction = session.beginTransaction();

			logger.info("Session opened to store the wishList for the item {}", subscribedList.getId().getItemId(),
					"by {}", subscribedList.getId().getMemberId());

			boolean checkExistence = findExistance(subscribedList.getId().getMemberId(),
					subscribedList.getId().getItemId(), "subscribed item table");

			if (!checkExistence) {
				subscribedList.setcreatedTime(Utilities.getCurrentDateTime());
			}
			subscribedList.setmodifiedTime(Utilities.getCurrentDateTime());

			session.saveOrUpdate(subscribedList);
			logger.info("The item{}", subscribedList.getId().getItemId(), " subscribed by{}",
					subscribedList.getId().getMemberId(), "has been persisted");

			transaction.commit();
			logger.info("The item{}", subscribedList.getId().getItemId(), "subscribed by{}",
					subscribedList.getId().getMemberId(), "has been commited to DB");

			return true;
		} catch (Exception e) {
			logger.error("Not able to hit Subscribed table {}", e);
		}

		return false;
	}

}
