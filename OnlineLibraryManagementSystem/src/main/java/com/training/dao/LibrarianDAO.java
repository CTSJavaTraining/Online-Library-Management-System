package com.training.dao;

import com.training.blayer.BooksDTO;
import com.training.blayer.MoviesDTO;
import com.training.blayer.MusicDTO;

/**
 * interface holds abstract methods for class Librarian in dao.impl package
 * 
 * @author 447383
 *
 */
public interface LibrarianDAO {

	/**
	 * To check availability of the item
	 * 
	 * @param itemId
	 *            It is used to query ItemFormat table for checking the
	 *            availability.
	 * @return true if available else return false
	 */
	public String checkAvailability(String itemId);

	/**
	 * method used to add details of books to books table
	 * 
	 * @param booksDto
	 * @return
	 */
	public boolean addBooks(BooksDTO booksDto);

	/**
	 * allows to add items to movies table
	 * 
	 * @param moviesDto
	 * @return
	 */
	public boolean addMovies(MoviesDTO moviesDto);

	/**
	 * allows to add items to music table
	 * 
	 * @param musicDto
	 * @return
	 */
	public boolean addMovies(MusicDTO musicDto);

	/**
	 * this method is used to check if the item already exists to decide if to
	 * add or update a library item
	 * 
	 * @param itemName
	 * @param shortItemType
	 * @return
	 */
	public boolean itemExistence(String itemName, String shortItemType);
}
