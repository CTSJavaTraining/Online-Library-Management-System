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

	private String userSignUp() {

		UserDetails userdetails = new UserDetails();
		try (Session session = factory.openSession()) {
			session.beginTransaction();

			String role = userdetails.getRole();
			StringBuilder buildUserId = new StringBuilder();

			String userIdList = session.createQuery("SELECT userId FROM UserDetails DESC 1").getResultList().get(0)
					.toString();
			int userIdnumber = Integer.parseInt(userIdList.substring(1, userIdList.length()));

			if ("U".equals(role)) {

				buildUserId.append("U").append(userIdnumber + 1);

			} else if ("L".equals(role)) {

				buildUserId.append("L").append(userIdnumber + 1);

			}

			userdetails.setUserId(buildUserId.toString());

			List<AddressDetails> addressList = userdetails.getAddressDetails();
			for (AddressDetails address : addressList) {
				logger.info("Test address details {}", address.getCity());
				address.setUserDetails(userdetails);
				address.setCreatedTime(getCurrentDateTime());
				address.setModifiedTime(getCurrentDateTime());
			}
			userdetails.setcreatedTime(getCurrentDateTime());
			userdetails.setmodifiedTime(getCurrentDateTime());

			session.persist(userdetails);

			logger.info("Persisted user details ");
			session.getTransaction().commit();
			logger.info("Commited");

		}
		return null;
	}

	// Utility Method fot getting current date and time to store into Db
	private Date getCurrentDateTime() {
		return new Date();
	}
}
