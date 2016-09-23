package com.training.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.entity.AddressDetails;
import com.training.entity.UserDetails;
import com.training.utils.IDDateGeneratorUtility;

public class UserDAOImpl {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param userdetails
	 * @return
	 */
	public boolean userSignUp(UserDetails userdetails) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			String lastUserId = session
					.createQuery(
							"SELECT itemId FROM UserDetails where createdTime=(SELECT max(createdTime) FROM UserDetails")
					.getResultList().get(0).toString();

			userdetails.setUserId(IDDateGeneratorUtility.idGenerator(userdetails.getRole(),
					lastUserId.substring(0, 2).toUpperCase()));

			if (!userdetails.getAddressDetails().isEmpty()) {

				List<AddressDetails> addressList = userdetails.getAddressDetails();
				for (AddressDetails address : addressList) {
					logger.info("Test address details {}", address.getCity());
					address.setUserDetails(userdetails);
					address.setCreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
					address.setModifiedTime(IDDateGeneratorUtility.getCurrentDateTime());
				}

			}
			userdetails.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
			userdetails.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());

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

	public boolean validateUser(String username) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			logger.debug("User entered username is {} ", username);

			Query query = session.createQuery("FROM UserDetails WHERE userName = :uName");
			query.setParameter("uName", username);
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				return false;
			}
			return true;
		}
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public boolean validateLoginUser(String userId) {
		logger.info("Validating username {}", userId);
		try (Session session = sessionFactory.openSession()) {
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

	public boolean validateLogin(String userId, String password) {
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			String hql = "SELECT password FROM LoginDetails WHERE userId= :id";
			Query query = session.createQuery(hql);

			query.setParameter("id", userId);
			query.setMaxResults(1);

			String results = query.getResultList().get(0).toString();

			if (password.equalsIgnoreCase(results)) {
				return true;
			}
		}
		return false;
	}

	public Date getCurrentDateTime() {
		return new Date();
	}

}
