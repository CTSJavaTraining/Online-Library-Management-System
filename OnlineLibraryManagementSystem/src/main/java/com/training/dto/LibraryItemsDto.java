package com.training.dto;
// default package

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

/**
 * LibraryItemsDto for librarian to insert record
 */
public class LibraryItemsDto {

	private String itemId;

	@NotBlank(message = "itemName must not be blank!")
	@Size(min = 2, max = 20, message = "itemName must be atleast 2 characters long and max of 20 characters")
	private String itemName;

	@DateTimeFormat(pattern="YYYY")
	private Integer year;

	@NotBlank(message = "price must not be blank!")
	@Min(value = 10, message = "Minimum price should be atleast 10")
	private int price;

	@NotBlank(message = "description must not be blank!")
	private String description;

	@NotBlank(message = "itemType must not be blank!")
	@Size(min = 2, max = 10, message = "itemType must be atleast 2 characters long and max of 10 characters")
	private String itemType;

	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date dateAdded;

	@NotBlank(message = "noOfCopiesOrUrl must not be blank!")
	private String noOfCopiesOrUrl;

	@NotBlank(message = "category must not be blank!")
	private String category;

	@NotBlank(message = "releaseDate must not be blank!")
	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private Date releaseDate;

	private String genre;

	private Set<BooksDto> bookDetails;

	private Set<MoviesDto> movieDetails;

	private Set<MusicDto> musicDetails;

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
	public Set<BooksDto> getBookDetails() {
		return bookDetails;
	}

	/**
	 * @param bookDetails
	 *            the bookDetails to set
	 */
	public void setBookDetails(Set<BooksDto> bookDetails) {
		this.bookDetails = bookDetails;
	}

	/**
	 * @return the movieDetails
	 */
	public Set<MoviesDto> getMovieDetails() {
		return movieDetails;
	}

	/**
	 * @param movieDetails
	 *            the movieDetails to set
	 */
	public void setMovieDetails(Set<MoviesDto> movieDetails) {
		this.movieDetails = movieDetails;
	}

	/**
	 * @return the musicDetails
	 */
	public Set<MusicDto> getMusicDetails() {
		return musicDetails;
	}

	/**
	 * @param musicDetails
	 *            the musicDetails to set
	 */
	public void setMusicDetails(Set<MusicDto> musicDetails) {
		this.musicDetails = musicDetails;
	}

}
