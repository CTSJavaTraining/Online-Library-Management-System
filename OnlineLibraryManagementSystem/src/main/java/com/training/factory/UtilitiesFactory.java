package com.training.factory;

import java.util.Date;

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

	/**
	 * 
	 * @param categoryType
	 *            It is either user ROLE (Li for librarian , US for User) or
	 *            Item TYPE( MU - Music, MO - Movie, BO - Book)
	 * @param lastId
	 *            It is latest ID fetched from DB to generate new ID for new
	 *            item or user. If latest userid is US111111 then new generated
	 *            ID will be US111112
	 * @return new itemID or userID depending on catergoryType and latestID
	 */
	public static String idGenerator(String categoryType, String lastId) {

		StringBuilder stringBuilder = new StringBuilder();

		if (!lastId.isEmpty()) {

			logger.info("Latest Category ID is {} ", lastId);

			int categoryIdnumber = Integer.parseInt(lastId.substring(2, lastId.length()));

			return stringBuilder.append(categoryType).append(categoryIdnumber + 1).toString();

		} else {
			return stringBuilder.append(categoryType).append("000000").toString();
		}

	}
}
