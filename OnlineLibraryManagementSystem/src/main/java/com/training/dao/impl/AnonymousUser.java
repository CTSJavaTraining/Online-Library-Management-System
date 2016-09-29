package com.training.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.blayer.ViewItemsDto;
import com.training.dao.AnonymousUserDAO;
import com.training.entity.LibraryItems;
import com.training.utils.LibraryConstants;

/**
 * 
 * @author 447383
 *
 */
@Component(value = "anonymousUser")
public class AnonymousUser implements AnonymousUserDAO {

	private static final Logger logger = LoggerFactory.getLogger(AnonymousUser.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewItemsDto> searchItems(String itemName, int pageNo) {

		// Creating empty list to store result
		List<ViewItemsDto> viewItemsDtoList = Collections.emptyList();

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			int startResult = (pageNo - 1) * 10;
			// Querying all the items with the itemName
			Query query = session.createQuery("from LibraryItems where itemName = :itemName");
			query.setParameter("itemName", itemName);
			query.setFirstResult(startResult);
			query.setMaxResults(10);
			List<LibraryItems> resultList = query.getResultList();

			return viewItemsDtoList = getRequestedItems(resultList);

		} catch (Exception e) {
			logger.error(e + "Failed to retrieve the search results for {}", itemName);
		}
		return viewItemsDtoList;
	}

	private List<ViewItemsDto> getRequestedItems(List<LibraryItems> resultList) {
		List<ViewItemsDto> viewItemsDtoList;
		viewItemsDtoList = new ArrayList<>();

		for (LibraryItems libraryItems : resultList) {
			ViewItemsDto viewItemsdto = new ViewItemsDto();

			viewItemsdto.setItemName(libraryItems.getItemName());
			viewItemsdto.setItemType(libraryItems.getItemType());
			viewItemsdto.setYear(libraryItems.getYear());
			viewItemsdto.setPrice(libraryItems.getPrice());

			viewItemsDtoList.add(viewItemsdto);
		}

		logger.info("Size of the result sent for search item is {}", viewItemsDtoList.size());
		return viewItemsDtoList;
	}

	@Override
	public Map<String, List<ViewItemsDto>> viewItemsCheck(int pageNo) {

		int startResult = (pageNo - 1) * 3;
		System.out.println(startResult+"<--start result and page num -->"+pageNo);

		Map<String, List<ViewItemsDto>> allResults = new HashMap<>();

		allResults.put(LibraryConstants.BOOKS, runQuery(LibraryConstants.BOOKS, startResult));

		allResults.put(LibraryConstants.MOVIES, runQuery(LibraryConstants.MOVIES, startResult));

		allResults.put(LibraryConstants.MUSIC, runQuery(LibraryConstants.MUSIC, startResult));

		return allResults;
	}

	/**
	 * 
	 * @param itemType
	 * @param startResult
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ViewItemsDto> runQuery(String category, int startResult) {

		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from LibraryItems where category = :itemCategory");
			query.setParameter("itemCategory", category);
			query.setFirstResult(startResult);

			return getRequestedItems(query.setMaxResults(3).getResultList());

		} catch (Exception e) {
			logger.error("Exception {} Failed to hit table {}", e, category);
		}

		return Collections.emptyList();

	}

}
