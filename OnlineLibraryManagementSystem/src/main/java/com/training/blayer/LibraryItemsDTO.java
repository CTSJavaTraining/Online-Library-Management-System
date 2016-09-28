package com.training.blayer;
// default package

import java.util.Date;
import java.util.Set;

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

/**
 * LibraryItemsDTO for librarian to insert record
 */
public class LibraryItemsDTO {

	private String itemId;

	private String itemName;

	private Integer year;

	private int price;

	private String description;

	private String itemType;

	private Date dateAdded;

	private String noOfCopiesOrUrl;

	private String category;

	private Date releaseDate;

	private String genre;

	private Set<BooksDTO> bookDetails;

	private Set<MoviesDTO> movieDetails;

	private Set<MusicDTO> musicDetails;

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to Set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName
	 *            the itemName to Set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to Set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to Set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to Set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to Set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the dateAdded
	 */
	public Date getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded
	 *            the dateAdded to Set
	 */
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the noOfCopiesOrUrl
	 */
	public String getNoOfCopiesOrUrl() {
		return noOfCopiesOrUrl;
	}

	/**
	 * @param noOfCopiesOrUrl
	 *            the noOfCopiesOrUrl to Set
	 */
	public void setNoOfCopiesOrUrl(String noOfCopiesOrUrl) {
		this.noOfCopiesOrUrl = noOfCopiesOrUrl;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to Set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            the releaseDate to Set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to Set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the bookDetails
	 */
	public Set<BooksDTO> getBookDetails() {
		return bookDetails;
	}

	/**
	 * @param bookDetails the bookDetails to set
	 */
	public void setBookDetails(Set<BooksDTO> bookDetails) {
		this.bookDetails = bookDetails;
	}

	/**
	 * @return the movieDetails
	 */
	public Set<MoviesDTO> getMovieDetails() {
		return movieDetails;
	}

	/**
	 * @param movieDetails the movieDetails to set
	 */
	public void setMovieDetails(Set<MoviesDTO> movieDetails) {
		this.movieDetails = movieDetails;
	}

	/**
	 * @return the musicDetails
	 */
	public Set<MusicDTO> getMusicDetails() {
		return musicDetails;
	}

	/**
	 * @param musicDetails the musicDetails to set
	 */
	public void setMusicDetails(Set<MusicDTO> musicDetails) {
		this.musicDetails = musicDetails;
	}



}
