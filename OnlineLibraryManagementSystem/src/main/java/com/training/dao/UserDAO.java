package com.training.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.AddressDetails;
import com.training.entity.UserDetails;

public class UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public SessionFactory factory = new Configuration().configure().buildSessionFactory();;

	public boolean userSignUp(UserDetails userdetails) {

		try (Session session = factory.openSession()) {

			session.beginTransaction();

			StringBuilder buildUserId = new StringBuilder();
			List<String> userIdMaxList = session.createQuery("SELECT userId FROM UserDetails").getResultList();

			String role = userdetails.getRole();
			if (!userIdMaxList.isEmpty()) {

				String userIdMax = userIdMaxList.get(userIdMaxList.size() - 1).toString();
				logger.info("Latest USERID is {} ", userIdMax);
				int userIdnumber = Integer.parseInt(userIdMax.substring(1, userIdMax.length()));

				buildUserId.append(role).append(userIdnumber + 1);

			} else {
				buildUserId.append(role).append(00000);
			}

			userdetails.setUserId(buildUserId.toString());

			if (userdetails.getAddressDetails() != null) {

				List<AddressDetails> addressList = userdetails.getAddressDetails();
				for (AddressDetails address : addressList) {
					logger.info("Test address details {}", address.getCity());
					address.setUserDetails(userdetails);
					address.setCreatedTime(getCurrentDateTime());
					address.setModifiedTime(getCurrentDateTime());
				}

			}
			userdetails.setcreatedTime(getCurrentDateTime());
			userdetails.setmodifiedTime(getCurrentDateTime());

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
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			logger.debug("User entered username is {} ", username);

			Query query = session.createQuery("FROM UserDetails WHERE userName = :uName");
			query.setParameter("uName", username);
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				return false;
			} else {
				return true;
			}
		}
	}

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

	// Utility Method fot getting current date and time to store into Db
	public Date getCurrentDateTime() {
		return new Date();
	}
}
