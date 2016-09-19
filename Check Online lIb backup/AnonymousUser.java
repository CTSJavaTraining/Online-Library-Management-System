package com.training.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transaction;
import org.hibernate.Session;

import com.training.entity.LibraryItems;
import com.training.factory.ApplicationSessionFactory;

/**
 * 
 * @author 447383
 *
 */
public class AnonymousUser {
	/**
	 * this method is used to search the items by name
	 * 
	 * @param name
	 */
	public void searchItems(String name) {
		/*Session session = ApplicationSessionFactory.returnFactory().openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		try {

			String hqlQuery = "from LibraryItems where item_name = :itemName";
			Query query = (Query) session.createQuery(hqlQuery);
			query.setParameter("item_name", name);
			List<?> listResult = ((org.hibernate.Query<?>) query).list();

			if (listResult.isEmpty()) {
				System.out.println("no matches found");
			}

			else {
				for (Object libraryItems : listResult) {

					System.out.println(((LibraryItems) libraryItems).getItemName());

				}
			}

			transaction.commit();
		} catch (Exception e) {
			System.err.print(e);
		} finally {
			session.close();
		}*/

	}

	/**
	 * this method provides anonymous user the facility to view all the items
	 */
	public void viewItems() {
		Session session = ApplicationSessionFactory.returnFactory().openSession();
		Transaction transaction = (Transaction) session.beginTransaction();

		try {

			String hqlQuery = "from LibraryItems";
			Query query = (Query) session.createQuery(hqlQuery);
			List<?> listResult = ((org.hibernate.Query<?>) query).list();

			if (listResult.isEmpty()) {
				System.out.println("no items found");
			}

			else {
				for (Object libraryItems : listResult) {

					System.out.println(((LibraryItems) libraryItems).getItemName());

				}
			}

			transaction.commit();
		} catch (Exception e) {
			System.err.print(e);
		} finally {
			session.close();
		}

	}

}
