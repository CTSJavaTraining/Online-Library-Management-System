package com.training.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this class creates and provides session factory
 * 
 * @author 447383
 *
 */
public class Utilities {

	private static final Logger logger = LoggerFactory.getLogger(Utilities.class);

	private Utilities() {

	}

	// Utility Method for getting current date and time to store into Db
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

			stringBuilder.append(categoryType.substring(0, 2).toUpperCase()); // Example:
																				// LI
			// or DV

			// Converting "US111111" to integer 111111 and adding 1 to 111112
			// which is then converted to String

			String categoryIdnumber = Integer.toString(Integer.parseInt(lastId.substring(2, lastId.length())) + 1);

			// Prepending 0's. if categoryIdnumber is "2", then 0 are prepended
			// to 6 digits as 000002

			for (int toPrepend = 6 - categoryIdnumber.length(); toPrepend > 0; toPrepend--) {
				stringBuilder.append('0');
			}

			return stringBuilder.append(categoryIdnumber).toString();

		} else {
			return stringBuilder.append(categoryType.substring(0, 2).toUpperCase()).append("000000").toString();
		}

	}

}
