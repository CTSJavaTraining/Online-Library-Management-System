package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RatingTable class is an entity model of rating_table table in database
 */
@Entity
@Table(name = "rating_table")
public class RatingTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false, length = 6)),
			@AttributeOverride(name = "itemId", column = @Column(name = "item_id", nullable = false, length = 8)) })
	private RatingTableId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false, insertable = false, updatable = false)
	private LibraryItems libraryItems;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private UserDetails userDetails;

	@Column(name = "rating", nullable = false)
	private int rating;

	@Column(name = "review", nullable = false, length = 200)
	private String review;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	public RatingTableId getId() {
		return this.id;
	}

	public void setId(RatingTableId id) {
		this.id = id;
	}

	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	public UserDetails getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReview() {
		return this.review;
	}

	public void setReview(String review) {
		this.review = review;
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

}
