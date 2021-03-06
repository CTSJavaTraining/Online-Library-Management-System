package com.training.entity;
// default package

// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Movies class is an entity model of movies table in database
 */
@Entity
@Table(name = "movies")
public class Movies implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "movie_id", length = 8)
	private String movieId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "movie_id")
	private LibraryItems libraryItems;

	@Column(name = "productions", nullable = false, length = 100)
	private String productions;

	@Column(name = "writers", nullable = false, length = 100)
	private String writers;

	@Column(name = "singers", nullable = false, length = 100)
	private String singers;

	@Column(name = "directors", nullable = false, length = 100)
	private String directors;

	@Column(name = "casts", nullable = false, length = 100)
	private String casts;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date", nullable = false, length = 10)
	private Date releaseDate;

	@Column(name = "genre", length = 20)
	private String genre;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	private Date createdTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	private Date modifiedTime;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	public String getProductions() {
		return this.productions;
	}

	public void setProductions(String productions) {
		this.productions = productions;
	}

	public String getWriters() {
		return this.writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public String getSingers() {
		return this.singers;
	}

	public void setSingers(String singers) {
		this.singers = singers;
	}

	public String getDirectors() {
		return this.directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getCasts() {
		return this.casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
