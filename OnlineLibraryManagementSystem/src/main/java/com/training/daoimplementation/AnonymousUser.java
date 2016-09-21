package com.training.daoimplementation;

import java.util.Collections;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.dao.AnonymousUserDAO;
import com.training.entity.LibraryItems;
import com.training.factory.UtilitiesFactory;

/**
 * 
 * @author 447383
 *
 */
public class AnonymousUser implements AnonymousUserDAO {

	private static final Logger logger = LoggerFactory.getLogger(AnonymousUser.class);
	private SessionFactory factory = UtilitiesFactory.returnFactory();

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryItems> searchItems(String name, String itemType) {
		Query query;
		List<LibraryItems> resultList = Collections.emptyList();
		try (Session session = factory.openSession()) {
			session.beginTransaction();
			query = session.createQuery("from library_items where itemType= :item and itemName = :name");
			query.setParameter("name", name);
			query.setParameter("item", itemType);
			resultList = query.getResultList();
			logger.info("Size of the result sent for search item {}", name, "is {}", resultList.size());
		} catch (Exception e) {
			logger.error(e + "Failed to retrieve the search results for {}", name);
		}
		return resultList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<LibraryItems> viewItems(int maxValue) {
		Query query;
		String hqlQuery = "from library_items where itemType= :itemType";
		List<LibraryItems> resultList = Collections.emptyList();
		List<LibraryItems> resultListAll = Collections.emptyList();
		try (Session session = factory.openSession()) {
			session.beginTransaction();

			query = session.createQuery(hqlQuery);
			query.setParameter("itemType", "Books");
			resultList = query.setMaxResults(maxValue).getResultList();
			resultListAll.addAll(resultList);
			logger.info("Size of the result sent for Books Category to view is {}", resultList.size());

			query = session.createQuery(hqlQuery);
			query.setParameter("itemType", "Music");
			resultList = query.setMaxResults(maxValue).getResultList();
			resultListAll.addAll(resultList);
			resultList.clear();
			logger.info("Size of result for Music Category to view is {}", resultList.size());

			query = session.createQuery(hqlQuery);
			query.setParameter("itemType", "Movies");
			resultList = query.setMaxResults(maxValue).getResultList();
			resultListAll.addAll(resultList);
			resultList.clear();
			logger.info("Size of result sent for Movies Category to view is {}", resultList.size());

			logger.info("Total Search Result sent {}", resultListAll.size());

		} catch (Exception e) {
			logger.error(e.getMessage(), "Failed to retrieve the results for view");
		}
		return resultListAll;
	}

}
