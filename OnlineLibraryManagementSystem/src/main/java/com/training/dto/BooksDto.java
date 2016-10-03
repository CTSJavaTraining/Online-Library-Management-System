package com.training.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Dto class created to set details of book present in library
 * 
 * @author 542224
 *
 */
public class BooksDto {

	@NotBlank(message = "author must not be blank!")
	private String author;

	@NotBlank(message = "publishers must not be blank!")
	private String publishers;

	private BigDecimal editionNo;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishers() {
		return publishers;
	}

	public void setPublishers(String publishers) {
		this.publishers = publishers;
	}

	public BigDecimal getEditionNo() {
		return editionNo;
	}

	public void setEditionNo(BigDecimal editionNo) {
		this.editionNo = editionNo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((editionNo == null) ? 0 : editionNo.hashCode());
		result = prime * result + ((publishers == null) ? 0 : publishers.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksDto other = (BooksDto) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (editionNo == null) {
			if (other.editionNo != null)
				return false;
		} else if (!editionNo.equals(other.editionNo))
			return false;
		if (publishers == null) {
			if (other.publishers != null)
				return false;
		} else if (!publishers.equals(other.publishers))
			return false;
		return true;
	}

}
