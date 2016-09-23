package com.training.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.blayer.BooksDTO;
import com.training.blayer.LibraryItemsDTO;
import com.training.entity.Books;
import com.training.entity.LibraryItems;
import com.training.entity.Movies;
import com.training.entity.Music;
import com.training.utils.IDDateGeneratorUtility;

@Component
public class LibrarianDAO {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private LibraryItemsDTO libraryItemsDto;

	@Autowired
	private LibraryItems libraryItems;

	@Autowired
	private Books books;

	@Autowired
	private Movies movies;

	@Autowired
	private Music music;

	public boolean addItems(LibraryItems libraryItems) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			String lastItemId = session
					.createQuery(
							"SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems")
					.getResultList().get(0).toString();

			libraryItems.setItemId(IDDateGeneratorUtility.idGenerator(libraryItems.getItemType(),
					lastItemId.substring(0, 2).toUpperCase()));

			if (!libraryItems.getBooks().isEmpty()) {
				List<Books> bookList = libraryItems.getBooks();
				for (Books book : bookList) {
					logger.info("Test book details {}", book.getPublishers());
					book.setLibraryItems(libraryItems);
					book.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
					book.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());
				}

			} else if (!libraryItems.getMovies().isEmpty()) {
				List<Movies> movieList = libraryItems.getMovies();
				for (Movies movie : movieList) {
					logger.info("Test movie details {}", movie.getDirectors());
					movie.setLibraryItems(libraryItems);
					movie.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
					movie.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());
				}
			}

			else if (!libraryItems.getMusic().isEmpty()) {
				List<Music> musicList = libraryItems.getMusic();
				for (Music musicItem : musicList) {
					logger.info("Test movie details {}", musicItem.getGenre());
					musicItem.setLibraryItems(libraryItems);
					musicItem.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
					musicItem.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());
				}
			}

			libraryItems.setcreatedTime(IDDateGeneratorUtility.getCurrentDateTime());
			libraryItems.setmodifiedTime(IDDateGeneratorUtility.getCurrentDateTime());

			session.saveOrUpdate(libraryItems);

			logger.info("Persisted item details ");
			session.getTransaction().commit();
			logger.info("Commited");

			return true;
		} catch (Exception e) {
			logger.error("Exception is thrown {}", e);
			return false;
		}
	}

	/**
	 * To check availability of the item
	 * 
	 * @param itemId
	 *            It is used to query ItemFormat table for checking the
	 *            availability.
	 * @return true if available else return false
	 */
	public String checkAvailability(String itemId) {
		try (Session session = sessionFactory.openSession()) {

			logger.debug("Checking availability for itemID {}", itemId);

			String availabilityStatus = session.createQuery("SELECT available from ItemFormat where itemId =:itemId")
					.setParameter("itemId", itemId).getResultList().get(0).toString();

			if (!availabilityStatus.isEmpty()) {
				logger.debug("Item is available {}", availabilityStatus);
				return availabilityStatus;
			}
		} catch (Exception e) {

			logger.error("Exception occured {}", e);
			return "Error";
		}
		return null;
	}

	/**
	 * 
	 * @param booksDto
	 * @return
	 */
	public boolean addBooks(BooksDTO booksDto) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();

			String lastItemId = session
					.createQuery(
							"SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems")
					.getResultList().get(0).toString();

			booksDto.setItemId(IDDateGeneratorUtility.idGenerator(booksDto.getItemType(),
					lastItemId.substring(0, 2).toUpperCase()));

			libraryItems.setItemId(booksDto.getItemId());
			libraryItems.setItemName(booksDto.getItemName());
			libraryItems.setItemType(booksDto.getItemType());
			libraryItems.setYear(booksDto.getYear());
			libraryItems.setPrice(booksDto.getPrice());
			libraryItems.setCategory(booksDto.getCategory());
			libraryItems.setDateAdded(booksDto.getDateAdded());
			libraryItems.setDescription(booksDto.getDescription());

			books.setLibraryItems(libraryItems);
			books.setAuthor(booksDto.getAuthor());
			books.setPublishers(booksDto.getPublishers());
			books.setEditionNo(booksDto.getEditionNo());
			books.setReleaseDate(libraryItemsDto.getReleaseDate());
			books.setGenre(libraryItemsDto.getGenre());

			session.persist(libraryItems);
			logger.debug("Persisted libraryItems records. itemID: {}", libraryItems.getItemId());

			session.persist(books);
			logger.debug("Persisted book records. book Author: {}", books.getAuthor());

			session.getTransaction().commit();
			logger.debug("Commited changes for book item of itemID: {}", libraryItems.getItemId());

			return true;

		}

	}

	public boolean itemExistence(String itemName, String ShortItemType) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			logger.debug("User entered username is {} ", itemName);

			Query query = session.createQuery("FROM LibraryItems WHERE itemName = :iName AND itemId LIKE :iType");
			query.setParameter("iName", itemName);
			query.setParameter("iType", ShortItemType+"%");
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				return false;
			}
			return true;
		}
	}

}
