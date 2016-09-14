package com.training.DAO;
// default package
// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Music generated by hbm2java
 */
@Entity
@Table(name = "music", catalog = "onlinelibrary")
public class Music implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String musicId;
	private LibraryItems libraryItems;
	private String productions;
	private String writers;
	private String singers;
	private Date releaseDate;
	private String genre;
	private Date CTime;
	private Date MTime;

	public Music() {
	}

	public Music(LibraryItems libraryItems, String productions, String writers, String singers, Date releaseDate,
			Date CTime, Date MTime) {
		this.libraryItems = libraryItems;
		this.productions = productions;
		this.writers = writers;
		this.singers = singers;
		this.releaseDate = releaseDate;
		this.CTime = CTime;
		this.MTime = MTime;
	}

	public Music(LibraryItems libraryItems, String productions, String writers, String singers, Date releaseDate,
			String genre, Date CTime, Date MTime) {
		this.libraryItems = libraryItems;
		this.productions = productions;
		this.writers = writers;
		this.singers = singers;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.CTime = CTime;
		this.MTime = MTime;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "libraryItems"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "music_id", unique = true, nullable = false, length = 8)
	public String getMusicId() {
		return this.musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	@Column(name = "productions", nullable = false, length = 100)
	public String getProductions() {
		return this.productions;
	}

	public void setProductions(String productions) {
		this.productions = productions;
	}

	@Column(name = "writers", nullable = false, length = 100)
	public String getWriters() {
		return this.writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	@Column(name = "singers", nullable = false, length = 100)
	public String getSingers() {
		return this.singers;
	}

	public void setSingers(String singers) {
		this.singers = singers;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "release_date", nullable = false, length = 10)
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "genre", length = 20)
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "c_time", nullable = false, length = 19)
	public Date getCTime() {
		return this.CTime;
	}

	public void setCTime(Date CTime) {
		this.CTime = CTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "m_time", nullable = false, length = 19)
	public Date getMTime() {
		return this.MTime;
	}

	public void setMTime(Date MTime) {
		this.MTime = MTime;
	}

}