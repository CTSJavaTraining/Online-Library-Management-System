package com.training.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transaction;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.training.factory.ApplicationSessionFactory;

/**
 * 
 * @author 447383
 *
 */
public class AnonymousUser {

	private static final Logger logger = Logger.getLogger(AnonymousUser.class);

	/**
	 * this method is used to search the items by name
	 * 
	 * @param name
	 */
	public void searchItems(String name) {

		Query query;
		Session session = ApplicationSessionFactory.returnFactory().openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		try {
			String hqlQuery = "from LibraryItems where item_name = :itemName";
			query = session.createQuery(hqlQuery);
			query.setParameter("item_name", name);
			List<?> listResult = query.getResultList();

			if (listResult.isEmpty()) {
				logger.info("No matches found for search string " + name);
			}

			else {
				listResult = displayItems(listResult);
			}

			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "Failed to retrieve the search results for " + name);
		} finally {
			session.close();
		}
	}

	/**
	 * this method provides anonymous user the facility to view all the items
	 */
	public void viewItems() {
		Query query;
		SessionFactory factory =  ApplicationSessionFactory.returnFactory();
		Session session =factory.openSession();
		Transaction transaction = (Transaction) session.beginTransaction();
		try {
			String hqlQuery = "from LibraryItems";
			query = session.createQuery(hqlQuery);
			List<?> listResult = query.getResultList();

			if (listResult.isEmpty()) {
				logger.info("No items were displayed for the user to view in general");
			}

			else {
				listResult = displayItems(listResult);
				System.out.println("Displayed");
			}

			transaction.commit();
		} catch (Exception e) {
			logger.error(e + "failed to display the items for anonymous user");
		} finally {
			session.close();
		}

	}

	/**
	 * 
	 * @param listResult
	 */
	private List<?> displayItems(List<?> listResult) {

		return listResult;
	}

	public static void main(String args[]) {
		AnonymousUser au = new AnonymousUser();
		au.viewItems();
		au.searchItems("hello");
	}
}
