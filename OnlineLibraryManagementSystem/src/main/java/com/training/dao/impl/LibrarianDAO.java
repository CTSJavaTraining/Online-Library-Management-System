package com.training.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.training.blayer.BooksDTO;
import com.training.entity.Books;
import com.training.entity.LibraryItems;
import com.training.entity.Movies;
import com.training.entity.Music;
import com.training.utils.UtilitiesFactory;

public class LibrarianDAO {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianDAO.class);

	@Autowired
	private SessionFactory sessionFactory;

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

			libraryItems.setItemId(
					UtilitiesFactory.idGenerator(libraryItems.getItemType(), lastItemId.substring(0, 2).toUpperCase()));

			if (!libraryItems.getBooks().isEmpty()) {
				List<Books> bookList = libraryItems.getBooks();
				for (Books book : bookList) {
					logger.info("Test book details {}", book.getPublishers());
					book.setLibraryItems(libraryItems);
					book.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
					book.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());
				}

			} else if (!libraryItems.getMovies().isEmpty()) {
				List<Movies> movieList = libraryItems.getMovies();
				for (Movies movie : movieList) {
					logger.info("Test movie details {}", movie.getDirectors());
					movie.setLibraryItems(libraryItems);
					movie.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
					movie.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());
				}
			}

			else if (!libraryItems.getMusic().isEmpty()) {
				List<Music> musicList = libraryItems.getMusic();
				for (Music musicItem : musicList) {
					logger.info("Test movie details {}", musicItem.getGenre());
					musicItem.setLibraryItems(libraryItems);
					musicItem.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
					musicItem.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());
				}
			}

			libraryItems.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
			libraryItems.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());

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

		books.setPublishers(booksDto.getPublishers());
		books.setGenre(booksDto.getGenre());
		books.setEditionNo(booksDto.getEditionNo());
		books.setAuthor(booksDto.getAuthor());

		return false;

	}

}
