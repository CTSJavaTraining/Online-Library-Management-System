package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LibraryItems class is an entity model of library_items table in Database
 */
@Entity
@Table(name = "library_items")
public class LibraryItems implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "item_id", unique = true, nullable = false, length = 8)
	private String itemId;

	@Column(name = "item_name", nullable = false, length = 50)
	private String itemName;

	@Column(name = "year")
	private Integer year;

	@Column(name = "price", nullable = false)
	private int price;

	@Column(name = "description", nullable = false, length = 200)
	private String description;

	@Column(name = "item_type", nullable = false, length = 10)
	private String itemType;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_added", nullable = false, length = 10)
	private Date dateAdded;

	@Column(name = "category", length = 10)
	private String category;

	@Column(name = "rating", length = 2)
	private int rating;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private List<RatingTable> ratingTables;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private List<LikedList> likedLists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private List<WishList> wishLists;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private Books books;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private Music music;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private Movies movies;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private List<SubscribedList> subscribedLists;

	@OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "libraryItems")
	private ItemFormat itemFormats;

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Date getDateAdded() {
		return this.dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * 
	 * @return the createdTime
	 */
	public Date getcreatedTime() {
		return this.createdTime;
	}

	/**
	 * the createdTime to be set
	 * 
	 * @param createdTime
	 */
	public void setcreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * 
	 * @return the modifiedTime
	 */
	public Date getmodifiedTime() {
		return this.modifiedTime;
	}

	/**
	 * the modifiedTime to be set
	 * 
	 * @param modifiedTime
	 */
	public void setmodifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<RatingTable> getRatingTables() {
		return this.ratingTables;
	}

	public void setRatingTables(List<RatingTable> ratingTables) {
		this.ratingTables = ratingTables;
	}

	public List<LikedList> getLikedLists() {
		return this.likedLists;
	}

	public void setLikedLists(List<LikedList> likedLists) {
		this.likedLists = likedLists;
	}

	public List<WishList> getWishLists() {
		return this.wishLists;
	}

	public void setWishLists(List<WishList> wishLists) {
		this.wishLists = wishLists;
	}

	public Books getBooks() {
		return this.books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public Movies getMovies() {
		return movies;
	}

	public void setMovies(Movies movies) {
		this.movies = movies;
	}

	public List<SubscribedList> getSubscribedLists() {
		return this.subscribedLists;
	}

	public void setSubscribedLists(List<SubscribedList> subscribedLists) {
		this.subscribedLists = subscribedLists;
	}

	public ItemFormat getItemFormats() {
		return this.itemFormats;
	}

	public void setItemFormats(ItemFormat itemFormats) {
		this.itemFormats = itemFormats;
	}

}
