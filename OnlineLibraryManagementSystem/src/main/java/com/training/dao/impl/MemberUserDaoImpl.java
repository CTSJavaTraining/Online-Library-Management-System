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
import com.training.entity.SubscribedListId;
import com.training.entity.WishList;
import com.training.entity.WishListId;
import com.training.utils.LibraryConstants;
import com.training.utils.Utilities;

/**
 * this class holds the subscribe and add wish list by the user
 * 
 * @author 447383
 *
 */
public class MemberUserDaoImpl extends AnonymousUserDaoImpl implements MemberUserDao {

	private static final Logger logger = LoggerFactory.getLogger(MemberUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean findExistance(int memberId, String itemId, String tableName) {
		String hqlQuery = "from " + tableName + " where itemId = :itemId and memberID= :userId";
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			Query query = session.createQuery(hqlQuery);
			query.setParameter("userId", memberId);
			query.setParameter("itemId", itemId);
			List<?> listResult = query.getResultList();

			if (!listResult.isEmpty()) {
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
			WishListId id = wishList.getId();
			logger.info("Session opened to store the wishList for the item {}", id.getItemId(), " by {}",
					id.getMemberId());

			if (!findExistance(id.getMemberId(), id.getItemId(), LibraryConstants.WISHLISTTABLETABLE)) {
				wishList.setcreatedTime(Utilities.getCurrentDateTime());
			}
			wishList.setmodifiedTime(Utilities.getCurrentDateTime());

			session.saveOrUpdate(wishList);
			logger.info("The wishList table is updated for item{}", id.getItemId(), " by {}", id.getMemberId());

			transaction.commit();

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

			SubscribedListId id = subscribedList.getId();
			logger.info("Session opened to store the wishList for the item {}", id.getItemId(), "by {}",
					id.getMemberId());

			if (!findExistance(id.getMemberId(), id.getItemId(), LibraryConstants.SUBSCRIBEDTABLE)) {
				subscribedList.setcreatedTime(Utilities.getCurrentDateTime());
			}
			subscribedList.setmodifiedTime(Utilities.getCurrentDateTime());

			session.saveOrUpdate(subscribedList);
			logger.info("The item{}", id.getItemId(), " subscribed by{}", id.getMemberId(), "has been persisted");

			transaction.commit();

			return true;
		} catch (Exception e) {
			logger.error("Not able to hit Subscribed table {}", e);
		}

		return false;
	}

}
