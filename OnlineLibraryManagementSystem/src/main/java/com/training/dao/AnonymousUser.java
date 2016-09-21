package com.training.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.LibraryItems;
import com.training.factory.UtilitiesFactory;

/**
 * 
 * @author 447383
 *
 */
public class AnonymousUser {

	private static final Logger logger = LoggerFactory.getLogger(AnonymousUser.class);
	private Query query;
	private SessionFactory factory = UtilitiesFactory.returnFactory();

	/**
	 * 
	 * @param name
	 * @return
	 */

	public List<?> searchItems(String name) {

		List<?> listResult = Collections.emptyList();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			query = session.createQuery("from LibraryItems where itemName = :itemName");
			query.setParameter("itemName", name);
			listResult = query.getResultList();

			if (listResult.isEmpty()) {

				logger.info("No matches found for search string " + name);
			}
		} catch (Exception e) {
			logger.error(e + "Failed to retrieve the search results for " + name);
		}

		return listResult;
	}

	/**
	 * 
	 * @return
	 */

	public List<?> viewItems() {

		List<?> listResult = Collections.emptyList();
		try (Session session = factory.openSession()) {

			session.beginTransaction();
			query = session.createQuery("from LibraryItems ");
			listResult = (List<?>) query.setMaxResults(10);

			if (listResult.isEmpty()) {

				logger.info("No items were displayed for the user to view in general");
			}

		} catch (Exception e) {
			logger.error(e + "failed to display the items for anonymous user");
		}
		return listResult;
	}

}
