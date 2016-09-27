package com.training.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.blayer.BooksDTO;
import com.training.blayer.DeleteItemsDTO;
import com.training.blayer.LibraryItemsDTO;
import com.training.blayer.MoviesDTO;
import com.training.blayer.MusicDTO;
import com.training.dao.LibrarianDAO;
import com.training.entity.Books;
import com.training.entity.ItemFormat;
import com.training.entity.LibraryItems;
import com.training.entity.Movies;
import com.training.entity.Music;
import com.training.utils.LibraryConstants;
import com.training.utils.Utilities;

/**
 * class holds functionalities for Librarian
 * 
 * @author 447383
 *
 */
@Component
public class LibrarianUser implements LibrarianDAO {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianUser.class);

	@Autowired
	private SessionFactory sessionFactory;

	private static String getLatestItemId = "SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems) AND itemId LIKE :itemIdType";

	@Override
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
			return LibraryConstants.ERROR;
		}
		return LibraryConstants.EXISTS;
	}

	@Override
	public boolean itemExistence(String itemName, String shortItemType) {

		try (Session session = sessionFactory.openSession()) {

			logger.info("Checking for item existence {},{}", itemName, shortItemType);
			session.beginTransaction();

			logger.info("User entered item name is {}", itemName, " itemtype is {}", shortItemType);

			Query query = session
					.createQuery("SELECT itemId FROM LibraryItems WHERE itemName = :iName AND itemType LIKE :iType");
			query.setParameter("iName", itemName);
			query.setParameter("iType", shortItemType + "%");

			System.out.println("query performed--------------------------");
			if (query.getResultList().isEmpty()) {
				logger.info("Query result is empty. No item with this name: {}", itemName);
				return true;
			}

		} catch (Exception e) {
			logger.error("Not able to find existance of {} ", itemName, "of type {}", shortItemType, e);
		}
		logger.debug("Item exists: {}" + itemName);
		return false;
	}

	@Override
	public boolean deleteLibraryItems(DeleteItemsDTO deleteItemsDto) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery(
					"UPDATE ItemFormat SET available= :available WHERE itemId= (SELECT itemId from LibraryItems WHERE itemName = :itemName AND itemType = :itemType)");
			query.setParameter("available", "Item Deleted");
			query.setParameter("itemName", deleteItemsDto.getItemName());
			query.setParameter("itemType", deleteItemsDto.getItemType());

			query.executeUpdate();
			session.getTransaction().commit();

			logger.debug("Deleted itemName: {} and itemType: {}", deleteItemsDto.getItemName(),
					deleteItemsDto.getItemType());
			return true;
		} catch (Exception e) {
			logger.error("Error occured when deleting item {}", deleteItemsDto.getItemName(), " for exception: {}", e);
		}
		return false;
	}

	@Override
	public boolean addLibraryItems(LibraryItemsDTO libraryItemsDto) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			String shortItemType = libraryItemsDto.getItemType().substring(0, 2).toUpperCase();

			@SuppressWarnings("unchecked")
			List<String> lastItemId = session.createQuery(getLatestItemId)
					.setParameter("itemIdType", shortItemType + "%").getResultList();

			// Generating latest Item ID
			if (!lastItemId.isEmpty()) {
				libraryItemsDto.setItemId(Utilities.idGenerator(libraryItemsDto.getItemType(), lastItemId.get(0)));
			} else {
				libraryItemsDto.setItemId(Utilities.idGenerator(libraryItemsDto.getItemType(), ""));
			}

			LibraryItems libraryItems = insertIntoLibraryItems(libraryItemsDto);

			session.saveOrUpdate(libraryItems);
			logger.debug("Persisted libraryItems records. itemID: {}", libraryItems.getItemId());

			session.getTransaction().commit();
			logger.debug("Commited changes for item of itemID: {}", libraryItems.getItemId());
			return true;

		} catch (Exception e) {

			logger.error("{} Not able to insert Item {}", e, libraryItemsDto.getItemId());

		}
		return false;

	}

	private LibraryItems insertIntoLibraryItems(LibraryItemsDTO libraryItemsDto) {
		LibraryItems libraryItems = setItemValues(libraryItemsDto);

		// Updating corresponding tables books or music or movies

		if ((LibraryConstants.BOOKS).equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setBookValues(libraryItemsDto, libraryItems);
		}

		else if ((LibraryConstants.MOVIES).equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setMovieValue(libraryItemsDto, libraryItems);
		}

		else if ((LibraryConstants.MUSIC).equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setMusicValue(libraryItemsDto, libraryItems);
		}

		// Updating item format table with availability
		setItemFormatValues(libraryItemsDto, libraryItems);
		return libraryItems;
	}

	private void setItemFormatValues(LibraryItemsDTO libraryItemsDto, LibraryItems libraryItems) {
		ItemFormat itemFormat = new ItemFormat();

		itemFormat.setItemId(libraryItemsDto.getItemId());
		itemFormat.setAvailable(libraryItemsDto.getNoOfCopiesOrUrl());

		// Updating itemtype in item_format table as Physical or Digital
		// Media
		if (libraryItemsDto.getItemType().startsWith("P")) {
			itemFormat.setItemType("Physical");
		} else {
			itemFormat.setItemType("Digital");
		}

		libraryItems.setItemFormats(itemFormat);
	}

	private void setMusicValue(LibraryItemsDTO libraryItemsDto, LibraryItems libraryItems) {
		Set<MusicDTO> musicList = libraryItemsDto.getMusicDto();

		Music music = new Music();

		for (MusicDTO musicValue : musicList) {
			music.setMusicId(libraryItemsDto.getItemId());
			music.setProductions(musicValue.getProductions());
			music.setSingers(musicValue.getSingers());
			music.setWriters(musicValue.getWriters());
			music.setReleaseDate(libraryItemsDto.getReleaseDate());
			music.setGenre(libraryItemsDto.getGenre());
			music.setcreatedTime(Utilities.getCurrentDateTime());
			music.setmodifiedTime(Utilities.getCurrentDateTime());

			libraryItems.setMusic(music);
		}
	}

	private void setMovieValue(LibraryItemsDTO libraryItemsDto, LibraryItems libraryItems) {
		Set<MoviesDTO> moviesList = libraryItemsDto.getMoviesDto();
		Movies movies = new Movies();

		for (MoviesDTO movieValue : moviesList) {
			movies.setMovieId(libraryItemsDto.getItemId());
			movies.setCasts(movieValue.getCasts());
			movies.setProductions(movieValue.getProductions());
			movies.setDirectors(movieValue.getDirectors());
			movies.setSingers(movieValue.getSingers());
			movies.setWriters(movieValue.getWriters());
			movies.setReleaseDate(libraryItemsDto.getReleaseDate());
			movies.setGenre(libraryItemsDto.getGenre());
			movies.setcreatedTime(Utilities.getCurrentDateTime());
			movies.setmodifiedTime(Utilities.getCurrentDateTime());

			libraryItems.setMovies(movies);
		}
	}

	private void setBookValues(LibraryItemsDTO libraryItemsDto, LibraryItems libraryItems) {
		Set<BooksDTO> booksList = libraryItemsDto.getBooksDto();
		Books books = new Books();

		for (BooksDTO bookValue : booksList) {

			books.setBookId(libraryItemsDto.getItemId());
			books.setAuthor(bookValue.getAuthor());
			books.setPublishers(bookValue.getPublishers());
			books.setEditionNo(bookValue.getEditionNo());
			books.setReleaseDate(libraryItemsDto.getReleaseDate());
			books.setGenre(libraryItemsDto.getGenre());
			books.setcreatedTime(Utilities.getCurrentDateTime());
			books.setmodifiedTime(Utilities.getCurrentDateTime());

			libraryItems.setBooks(books);
		}
	}

	private LibraryItems setItemValues(LibraryItemsDTO libraryItemsDto) {

		LibraryItems libraryItems = new LibraryItems();

		// Setting details in library tables
		libraryItems.setItemId(libraryItemsDto.getItemId());
		libraryItems.setItemName(libraryItemsDto.getItemName());
		libraryItems.setItemType(libraryItemsDto.getItemType());
		libraryItems.setYear(libraryItemsDto.getYear());
		libraryItems.setPrice(libraryItemsDto.getPrice());
		libraryItems.setCategory(libraryItemsDto.getCategory());
		libraryItems.setDateAdded(libraryItemsDto.getDateAdded());
		libraryItems.setDescription(libraryItemsDto.getDescription());
		libraryItems.setcreatedTime(Utilities.getCurrentDateTime());
		libraryItems.setmodifiedTime(Utilities.getCurrentDateTime());
		return libraryItems;
	}

}
