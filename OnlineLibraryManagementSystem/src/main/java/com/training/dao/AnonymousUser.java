package com.training.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.LibraryItems;
import com.training.factory.ApplicationSessionFactory;

/**
 * 
 * @author 447383
 *
 */
public class AnonymousUser {

	private static final Logger logger = LoggerFactory.getLogger(AnonymousUser.class);
	private Query query;
	private Session session;

	/**
	 * 
	 * @param name
	 * @return
	 */
	
	public List<LibraryItems> searchItems(String name) {

		String hqlQuery = "from LibraryItems where item_name = :itemName";
		List<LibraryItems> listResult = Collections.emptyList();
		try {
			session = ApplicationSessionFactory.returnFactory().openSession();
			session.beginTransaction();
			query = session.createQuery(hqlQuery);
			query.setParameter("itemName", name);
			listResult=query.getResultList();

			if ((listResult == null)||listResult.isEmpty()) {

				logger.info("No matches found for search string " + name);
			}
		} catch (Exception e) {
			logger.error(e + "Failed to retrieve the search results for " + name);
		} finally {
			if (session.isOpen())

				session.close();
		}

		return listResult;
	}

	/**
	 * 
	 * @return
	 */
	public List<LibraryItems> viewItems() {

		String hqlQuery = "from LibraryItems ";
		List<LibraryItems> listResult = Collections.emptyList();
		try {
			SessionFactory factory = ApplicationSessionFactory.returnFactory();
			session = factory.openSession();
			session.beginTransaction();
			query = session.createQuery(hqlQuery);
			listResult = (List<LibraryItems>) query.setMaxResults(10);

			if (listResult.equals(null) || listResult.isEmpty()) {

				logger.info("No items were displayed for the user to view in general");
			}

		} catch (Exception e) {
			logger.error(e + "failed to display the items for anonymous user");
		} finally {
			if (session.isOpen())

				session.close();
		}
		return listResult;
	}

}
