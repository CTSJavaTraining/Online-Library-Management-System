package com.training.blayer;

import java.math.BigDecimal;

public class BooksDTO {

	private String author;

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

}
