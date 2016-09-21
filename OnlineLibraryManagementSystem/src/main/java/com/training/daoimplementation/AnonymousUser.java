package com.training.daoimplementation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	public Map<String, List<LibraryItems>> viewItemsCheck(int maxValue) {

		List<LibraryItems> resultList;
		Map<String, List<LibraryItems>> allResults = Collections.emptyMap();

		resultList = runQuery("Books", maxValue);
		allResults.put("Books", resultList);
		resultList.clear();

		resultList = runQuery("Movies", maxValue);
		allResults.put("Movies", resultList);
		resultList.clear();

		resultList = runQuery("Music", maxValue);
		allResults.put("Music", resultList);
		resultList.clear();

		return allResults;
	}

	/**
	 * 
	 * @param itemType
	 * @param maxValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LibraryItems> runQuery(String itemType, int maxValue) {

		Query query;
		String hqlQuery = "from library_items where itemType= :itemType";
		List<LibraryItems> resultList = Collections.emptyList();
		try (Session session = factory.openSession()) {
			session.beginTransaction();

			query = session.createQuery(hqlQuery);
			query.setParameter("itemType", itemType);
			resultList = query.setMaxResults(maxValue).getResultList();
			logger.info("Size of the result sent for Category {}", itemType, "  to view is {}", resultList.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), " Failed to hit table {}", itemType);
		}
		resultList = Collections.emptyList();
		return resultList;

	}

}
