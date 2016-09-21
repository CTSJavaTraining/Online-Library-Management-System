package com.training.daoimplementation;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.entity.Books;
import com.training.entity.LibraryItems;
import com.training.entity.Movies;
import com.training.entity.Music;
import com.training.factory.UtilitiesFactory;

public class LibrarianDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public SessionFactory factory = UtilitiesFactory.returnFactory();

	public boolean addItems(LibraryItems libraryItems) {

		try (Session session = factory.openSession()) {

			session.beginTransaction();

			List<?> userIdMaxList = session.createQuery("SELECT itemId FROM libraryItems").getResultList();

			String itemType = libraryItems.getItemType().substring(0, 2).toString();

			String newItemID = UtilitiesFactory.idGenerator(itemType, userIdMaxList);

			libraryItems.setItemId(newItemID);

			if (libraryItems.getBooks() != null) {
				List<Books> bookList = libraryItems.getBooks();
				for (Books book : bookList) {
					logger.info("Test book details {}", book.getPublishers());
					book.setLibraryItems(libraryItems);
					book.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
					book.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());
				}

			} else if (libraryItems.getMovies() != null) {
				List<Movies> movieList = libraryItems.getMovies();
				for (Movies movie : movieList) {
					logger.info("Test movie details {}", movie.getDirectors());
					movie.setLibraryItems(libraryItems);
					movie.setcreatedTime(UtilitiesFactory.getCurrentDateTime());
					movie.setmodifiedTime(UtilitiesFactory.getCurrentDateTime());
				}
			}

			else if (libraryItems.getMusic() != null) {
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

}
