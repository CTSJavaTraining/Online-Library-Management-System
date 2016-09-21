package com.training.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.AddressDetails;
import com.training.entity.UserDetails;
import com.training.factory.UtilitiesFactory;

/**
 * 
 * @author 447482
 */
public class UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	private SessionFactory factory = UtilitiesFactory.returnFactory();

	/**
	 * 
	 * @param userdetails
	 * @return
	 */
	public boolean userSignUp(UserDetails userdetails) {

		try (Session session = factory.openSession()) {

			session.beginTransaction();

			List<?> userIdMaxList = session.createQuery("SELECT userId FROM UserDetails").getResultList();

			String role = userdetails.getRole();

			String newUserID = UtilitiesFactory.idGenerator(role, userIdMaxList);

			userdetails.setUserId(newUserID);

			if (userdetails.getAddressDetails() != null) {

				List<AddressDetails> addressList = userdetails.getAddressDetails();
				for (AddressDetails address : addressList) {
					logger.info("Test address details {}", address.getCity());
					address.setUserDetails(userdetails);
					address.setCreatedTime(UtilitiesFactory.getCurrentDateTime());
					address.setModifiedTime(UtilitiesFactory.getCurrentDateTime());
				}

			}
			userdetails.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
			userdetails.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());

			session.saveOrUpdate(userdetails);

			logger.info("Persisted user details ");
			session.getTransaction().commit();
			logger.info("Commited");

			return true;
		} catch (Exception e) {
			logger.error("Exception is thrown {}", e);
			return false;
		}
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean validateUser(String username) {
		logger.info("Validating username {}", username);
		try (Session session = factory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("FROM UserDetails WHERE userName = :uName");
			query.setParameter("uName", username);
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				logger.info("User does not exist");
				return true;
			}
			logger.info("userexists");
		}
		return false;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public boolean validateLoginUser(String userId) {
		logger.info("Validating username {}", userId);
		try (Session session = factory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("FROM UserDetails WHERE userId = :uId");
			query.setParameter("uId", userId);
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				logger.info("User does not exist");
				return true;
			}
			logger.info("userexists");
		}
		return false;
	}

	/**
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public int validateLogin(String userId, String password) {
		try (Session session = factory.openSession()) {

			session.beginTransaction();

			String hql = "SELECT password FROM LoginDetails WHERE userId= :id";
			Query query = session.createQuery(hql);

			query.setParameter("id", userId);
			query.setMaxResults(1);

			String results = query.getResultList().get(0).toString();

			if (results.isEmpty()) {
				return 0;
			} else if (password.equalsIgnoreCase(results)) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
