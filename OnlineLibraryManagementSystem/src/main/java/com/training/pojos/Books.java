package com.training.pojos;
// default package
// Generated Sep 13, 2016 5:13:51 PM by Hibernate Tools 5.1.0.Beta1


import java.io.Serializable;
import java.math.BigDecimal;
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
 * Books generated by hbm2java
 */
@Entity
@Table(name = "books", catalog = "onlinelibrary")
public class Books implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bookId;
	private LibraryItems libraryItems;
	private String author;
	private String publishers;
	private BigDecimal editionNo;
	private Date releaseDate;
	private String genre;
	private Date CTime;
	private Date MTime;

	public Books() {
	}

	public Books(LibraryItems libraryItems, String author, String publishers, BigDecimal editionNo, Date releaseDate,
			Date CTime, Date MTime) {
		this.libraryItems = libraryItems;
		this.author = author;
		this.publishers = publishers;
		this.editionNo = editionNo;
		this.releaseDate = releaseDate;
		this.CTime = CTime;
		this.MTime = MTime;
	}

	public Books(LibraryItems libraryItems, String author, String publishers, BigDecimal editionNo, Date releaseDate,
			String genre, Date CTime, Date MTime) {
		this.libraryItems = libraryItems;
		this.author = author;
		this.publishers = publishers;
		this.editionNo = editionNo;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.CTime = CTime;
		this.MTime = MTime;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "libraryItems"))
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "book_id", unique = true, nullable = false, length = 8)
	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public LibraryItems getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(LibraryItems libraryItems) {
		this.libraryItems = libraryItems;
	}

	@Column(name = "author", nullable = false, length = 50)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "publishers", nullable = false, length = 100)
	public String getPublishers() {
		return this.publishers;
	}

	public void setPublishers(String publishers) {
		this.publishers = publishers;
	}

	@Column(name = "edition_no", nullable = false, precision = 5)
	public BigDecimal getEditionNo() {
		return this.editionNo;
	}

	public void setEditionNo(BigDecimal editionNo) {
		this.editionNo = editionNo;
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