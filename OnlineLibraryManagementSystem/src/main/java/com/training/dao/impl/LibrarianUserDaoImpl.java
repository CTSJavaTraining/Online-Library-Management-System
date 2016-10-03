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

import com.training.dao.LibrarianUserDao;
import com.training.dto.BooksDto;
import com.training.dto.DeleteItemsDto;
import com.training.dto.LibraryItemsDto;
import com.training.dto.MoviesDto;
import com.training.dto.MusicDto;
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
@Component(value = "librarianUser")
public class LibrarianUserDaoImpl implements LibrarianUserDao {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianUserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

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

			// ShortItemtype is if the item is Physical Book then Query to check
			// for Name: "Item name" of type "PB%"
			query.setParameter("iType", shortItemType + "%");

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
	public boolean deleteLibraryItems(DeleteItemsDto deleteItemsDto) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			Query query = session.createQuery(
					"UPDATE ItemFormat SET available= :available WHERE itemId= (SELECT itemId from LibraryItems WHERE itemName = :itemName AND itemType = :itemType)");
			query.setParameter("available", "0");
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
	public boolean addLibraryItems(LibraryItemsDto libraryItemsDto) {

		try (Session session = sessionFactory.openSession()) {

			session.beginTransaction();
			String shortItemType = libraryItemsDto.getItemType().substring(0, 2).toUpperCase();
			String getLatestItemId = "SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems) AND itemId LIKE :itemIdType";
			@SuppressWarnings("unchecked")
			List<String> lastItemId = session.createQuery(getLatestItemId)
					.setParameter("itemIdType", shortItemType + "%").getResultList();

			getNewItemID(libraryItemsDto, lastItemId);

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

	/**
	 * 
	 * @param libraryItemsDto
	 * @param lastItemId
	 */
	private void getNewItemID(LibraryItemsDto libraryItemsDto, List<String> lastItemId) {
		// Generating latest Item ID
		if (!lastItemId.isEmpty()) {
			libraryItemsDto.setItemId(Utilities.idGenerator(libraryItemsDto.getItemType(), lastItemId.get(0)));
		} else {
			libraryItemsDto.setItemId(Utilities.idGenerator(libraryItemsDto.getItemType(), ""));
		}
	}

	/**
	 * 
	 * @param libraryItemsDto
	 * @return
	 */
	private LibraryItems insertIntoLibraryItems(LibraryItemsDto libraryItemsDto) {
		LibraryItems libraryItems = setItemValues(libraryItemsDto);

		// Updating corresponding tables books or music or movies

		if (LibraryConstants.BOOKS.equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setBookValues(libraryItemsDto, libraryItems);
		}

		else if (LibraryConstants.MOVIES.equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setMovieValue(libraryItemsDto, libraryItems);
		}

		else if (LibraryConstants.MUSIC.equalsIgnoreCase(libraryItemsDto.getCategory())) {
			setMusicValue(libraryItemsDto, libraryItems);
		}

		// Updating item format table with availability
		setItemFormatValues(libraryItemsDto, libraryItems);
		return libraryItems;
	}

	/**
	 * 
	 * @param libraryItemsDto
	 * @param libraryItems
	 */
	private void setItemFormatValues(LibraryItemsDto libraryItemsDto, LibraryItems libraryItems) {
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

	/**
	 * 
	 * @param libraryItemsDto
	 * @param libraryItems
	 */
	private void setMusicValue(LibraryItemsDto libraryItemsDto, LibraryItems libraryItems) {
		Set<MusicDto> musicList = libraryItemsDto.getMusicDetails();

		for (MusicDto musicValue : musicList) {
			Music music = new Music();
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

	/**
	 * 
	 * @param libraryItemsDto
	 * @param libraryItems
	 */
	private void setMovieValue(LibraryItemsDto libraryItemsDto, LibraryItems libraryItems) {
		Set<MoviesDto> moviesList = libraryItemsDto.getMovieDetails();

		for (MoviesDto movieValue : moviesList) {
			Movies movies = new Movies();
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

	/**
	 * 
	 * @param libraryItemsDto
	 * @param libraryItems
	 */
	private void setBookValues(LibraryItemsDto libraryItemsDto, LibraryItems libraryItems) {
		Set<BooksDto> booksList = libraryItemsDto.getBookDetails();

		for (BooksDto bookValue : booksList) {
			Books books = new Books();
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

	/**
	 * 
	 * @param libraryItemsDto
	 * @return
	 */
	private LibraryItems setItemValues(LibraryItemsDto libraryItemsDto) {

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