package com.training.dao.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.blayer.BooksDTO;
import com.training.blayer.LibraryItemsDTO;
import com.training.blayer.MoviesDTO;
import com.training.blayer.MusicDTO;
import com.training.dao.LibrarianDAO;
import com.training.entity.Books;
import com.training.entity.LibraryItems;
import com.training.entity.Movies;
import com.training.entity.Music;
import com.training.utils.IDDateGeneratorUtility;

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
			return "Error";
		}
		return "exist";
	}

	@Override
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
		} catch (Exception e) {
			logger.error("{} Not able to inser book {}", e, booksDto.getItemId());
			return false;
		}
		return true;

	}

	@Override
	public boolean addMovies(MoviesDTO moviesDto) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			String lastItemId = session
					.createQuery(
							"SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems")
					.getResultList().get(0).toString();

			moviesDto.setItemId(IDDateGeneratorUtility.idGenerator(moviesDto.getItemType(),
					lastItemId.substring(0, 2).toUpperCase()));

			libraryItems.setItemId(moviesDto.getItemId());
			libraryItems.setItemName(moviesDto.getItemName());
			libraryItems.setItemType(moviesDto.getItemType());
			libraryItems.setYear(moviesDto.getYear());
			libraryItems.setPrice(moviesDto.getPrice());
			libraryItems.setCategory(moviesDto.getCategory());
			libraryItems.setDateAdded(moviesDto.getDateAdded());
			libraryItems.setDescription(moviesDto.getDescription());

			movies.setLibraryItems(libraryItems);
			movies.setProductions(moviesDto.getProductions());
			movies.setWriters(moviesDto.getWriters());
			movies.setSingers(moviesDto.getSingers());
			movies.setDirectors(moviesDto.getDirectors());
			movies.setCasts(moviesDto.getCasts());
			movies.setReleaseDate(libraryItemsDto.getReleaseDate());
			movies.setGenre(libraryItemsDto.getGenre());

			session.persist(libraryItems);
			logger.info("Persisted object for library items{}", libraryItems.getItemId());

			session.persist(movies);
			logger.info("Persisted Object for movies details movies{}", moviesDto.getItemId());

			session.getTransaction().commit();
			logger.info("Commited Object {}", moviesDto.getItemId());
		} catch (Exception e) {
			logger.error("{} Not able to load movies detail into Db {}", moviesDto.getItemId(), e);
			return false;
		}
		return true;
	}

	@Override
	public boolean itemExistence(String itemName, String shortItemType) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			logger.debug("User entered username is {} ", itemName);

			Query query = session.createQuery("FROM LibraryItems WHERE itemName = :iName AND itemId LIKE :iType");
			query.setParameter("iName", itemName);
			query.setParameter("iType", shortItemType + "%");
			query.setMaxResults(1);

			if (query.getResultList().isEmpty()) {
				return false;
			}

		} catch (Exception e) {
			logger.error("Not able to find existance of {} ", itemName, "of type {}", shortItemType, e);
		}
		return true;
	}

	@Override
	public boolean addMusic(MusicDTO musicDto) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();

			String lastItemId = session
					.createQuery(
							"SELECT itemId FROM LibraryItems where createdTime=(SELECT max(createdTime) FROM LibraryItems")
					.getResultList().get(0).toString();

			musicDto.setItemId(IDDateGeneratorUtility.idGenerator(musicDto.getItemType(),
					lastItemId.substring(0, 2).toUpperCase()));

			libraryItems.setItemId(musicDto.getItemId());
			libraryItems.setItemName(musicDto.getItemName());
			libraryItems.setItemType(musicDto.getItemType());
			libraryItems.setYear(musicDto.getYear());
			libraryItems.setPrice(musicDto.getPrice());
			libraryItems.setCategory(musicDto.getCategory());
			libraryItems.setDateAdded(musicDto.getDateAdded());
			libraryItems.setDescription(musicDto.getDescription());

			music.setLibraryItems(libraryItems);
			music.setProductions(musicDto.getProductions());
			music.setWriters(musicDto.getWriters());
			music.setSingers(musicDto.getSingers());
			music.setGenre(libraryItemsDto.getGenre());
			music.setReleaseDate(libraryItemsDto.getReleaseDate());

			session.persist(libraryItems);
			logger.info("Persisted object for library items{}", libraryItems.getItemId());

			session.persist(movies);
			logger.info("Persisted Object for music details music{}", musicDto.getItemId());

			session.getTransaction().commit();

			logger.debug("Commited changes for music item of itemID: {}", libraryItems.getItemId());
		} catch (Exception e) {
			logger.error("{} Not able to inser music {}", e, musicDto.getItemId());
			return false;
		}
		return true;
	}

}
