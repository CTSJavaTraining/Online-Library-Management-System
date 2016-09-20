package com.training.factory;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this class creates and provides session factory
 * 
 * @author 447383
 *
 */
public class UtilitiesFactory {

	private static final Logger logger = LoggerFactory.getLogger(UtilitiesFactory.class);

	/**
	 * this method returns sessionfactory
	 * 
	 * @return
	 */
	public static SessionFactory returnFactory() {
		logger.info("Loading sessiong factory");
		return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	// Utility Method fot getting current date and time to store into Db
	public static Date getCurrentDateTime() {
		return new Date();
	}

	public static String idGenerator(String inputType, List<?> userIdMaxList) {
		StringBuilder buildUserId = new StringBuilder();

		if (!userIdMaxList.isEmpty()) {

			String userIdMax = userIdMaxList.get(userIdMaxList.size() - 1).toString();
			logger.info("Latest USERID is {} ", userIdMax);
			int userIdnumber = Integer.parseInt(userIdMax.substring(1, userIdMax.length()));

			return buildUserId.append(inputType).append(userIdnumber + 1).toString();

		} else {
			return buildUserId.append(inputType).append("00000").toString();
		}

	}
}
