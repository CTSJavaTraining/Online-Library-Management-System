package com.training.dao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.blayer.AddressDetailsDTO;
import com.training.blayer.UserSignupDTO;
import com.training.entity.AddressDetails;
import com.training.entity.LoginAudit;
import com.training.entity.LoginAuditId;
import com.training.entity.LoginDetails;
import com.training.entity.UserDetails;
import com.training.utils.Utilities;

@Component
public class UserDAOImpl {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private static String getLatestUserId = "SELECT userId FROM UserDetails where createdTime=(SELECT max(createdTime) FROM UserDetails) AND userId LIKE :userIdType";

	/**
	 * 
	 * @param userSignupDto
	 * @return
	 */

	public boolean userSignUp(UserSignupDTO userSignupDto) {
		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			// For user US and for Librarian LI
			String shortUserType = userSignupDto.getRole().substring(0, 2).toUpperCase();

			@SuppressWarnings("unchecked")
			List<String> lastUserId = session.createQuery(getLatestUserId)
					.setParameter("userIdType", shortUserType + "%").getResultList();

			getNewUserID(userSignupDto, lastUserId);

			UserDetails userDetails = insertUserDetails(userSignupDto);

			session.saveOrUpdate(userDetails);

			logger.info("Persisted user details ");
			session.getTransaction().commit();
			logger.info("Commited");

			return true;
		} catch (Exception e) {
			logger.error("Exception is thrown {}", e);
		}
		return false;
	}

	private void getNewUserID(UserSignupDTO userSignupDto, List<String> lastUserId) {
		// Generating latest User ID
		if (!lastUserId.isEmpty()) {
			userSignupDto.setUserId(Utilities.idGenerator(userSignupDto.getRole(), lastUserId.get(0)));
		} else {
			userSignupDto.setUserId(Utilities.idGenerator(userSignupDto.getRole(), ""));
		}
	}

	private UserDetails insertUserDetails(UserSignupDTO userSignupDto) {
		UserDetails userDetails = setNewUserDetails(userSignupDto);

		updatePassword(userSignupDto, userDetails);

		insertAddressValues(userSignupDto, userDetails);
		return userDetails;
	}

	private void insertAddressValues(UserSignupDTO userSignupDto, UserDetails userDetails) {
		List<AddressDetailsDTO> addresslist = userSignupDto.getAddressDetails();

		for (AddressDetailsDTO addressValue : addresslist) {
			AddressDetails addressDetails = new AddressDetails();

			addressDetails.setUserDetails(userDetails);
			addressDetails.setDoorno(addressValue.getDoorNo());
			addressDetails.setStreetName(addressValue.getStreetName());
			addressDetails.setCity(addressValue.getCity());
			addressDetails.setState(addressValue.getState());
			addressDetails.setCountry(addressValue.getCountry());
			addressDetails.setPinCode(addressValue.getPinCode());
			addressDetails.setCreatedTime(Utilities.getCurrentDateTime());
			addressDetails.setModifiedTime(Utilities.getCurrentDateTime());

			List<AddressDetails> addressDetailsList = new LinkedList<>();
			addressDetailsList.add(addressDetails);

			userDetails.setAddressDetails(addressDetailsList);
		}
	}

	private void updatePassword(UserSignupDTO userSignupDto, UserDetails userDetails) {
		LoginDetails loginDetails = new LoginDetails();

		loginDetails.setUserId(userSignupDto.getUserId());
		loginDetails.setPassword(userSignupDto.getConfirmPassword());
		loginDetails.setcreatedTime(Utilities.getCurrentDateTime());
		loginDetails.setmodifiedTime(Utilities.getCurrentDateTime());

		userDetails.setLoginDetails(loginDetails);

	}

	private UserDetails setNewUserDetails(UserSignupDTO userSignupDto) {
		UserDetails userDetails = new UserDetails();

		userDetails.setUserId(userSignupDto.getUserId());
		userDetails.setUserName(userSignupDto.getUserName());
		userDetails.setRole(userSignupDto.getRole());
		userDetails.setAge(userSignupDto.getAge());
		userDetails.setEmailId(userSignupDto.getEmailId());
		userDetails.setMobileNo(userSignupDto.getMobileNo());
		userDetails.setAlternateContactNo(userSignupDto.getAlternateContactNo());
		userDetails.setGender(userSignupDto.getGender());
		userDetails.setLanguages(userSignupDto.getLanguages());
		userDetails.setPreferredNotify(userSignupDto.getPreferredNotify());
		userDetails.setcreatedTime(Utilities.getCurrentDateTime());
		userDetails.setmodifiedTime(Utilities.getCurrentDateTime());
		return userDetails;
	}

	public boolean validateUser(String username) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			logger.debug("User entered username is {} ", username);

			Query query = session.createQuery("SELECT userId FROM UserDetails WHERE userName = :uName");
			query.setParameter("uName", username);
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				return true;
			}
			return false;
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

			return updateLoginAudit(userId, password, session, results);
		}
	}

	private boolean updateLoginAudit(String userId, String password, Session session, String results) {
		LoginAudit loginAudit = new LoginAudit();
		LoginAuditId loginAuditId = new LoginAuditId();

		loginAuditId.setUserId(userId);
		loginAuditId.setLastLoginTime(Utilities.getCurrentDateTime());
		loginAudit.setId(loginAuditId);

		if (password.equalsIgnoreCase(results)) {

			loginAudit.setLoginStatus('S');

			session.saveOrUpdate(loginAudit);
			session.getTransaction().commit();

			logger.debug(" Login audit updated successfully for userId {}", userId);

			return true;
		} else {

			loginAudit.setLoginStatus('F');
			session.saveOrUpdate(loginAudit);
			session.getTransaction().commit();

			logger.error("login audit update failed for userId {}", userId);

			return false;
		}
	}

}
