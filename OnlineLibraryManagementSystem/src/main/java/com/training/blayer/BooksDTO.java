package com.training.blayer;

import java.math.BigDecimal;

/**
 * 
 * @author 447482
 *
 */
public class BooksDTO extends LibraryItemsDTO {

	private String author;

	private String publishers;

	private BigDecimal editionNo;

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publishers
	 */
	public String getPublishers() {
		return publishers;
	}

	/**
	 * @param publishers
	 *            the publishers to set
	 */
	public void setPublishers(String publishers) {
		this.publishers = publishers;
	}

	/**
	 * @return the editionNo
	 */
	public BigDecimal getEditionNo() {
		return editionNo;
	}

	/**
	 * @param editionNo
	 *            the editionNo to set
	 */
	public void setEditionNo(BigDecimal editionNo) {
		this.editionNo = editionNo;
	}

}
