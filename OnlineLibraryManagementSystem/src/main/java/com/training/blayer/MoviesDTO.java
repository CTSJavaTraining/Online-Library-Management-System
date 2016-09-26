package com.training.blayer;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author 447482
 *
 */
public class MoviesDTO extends LibraryItemsDTO {

	@NotNull
	private String productions;

	@NotNull
	private String writers;

	@NotNull
	private String singers;

	@NotNull
	private String directors;

	@NotNull
	private String casts;

	/**
	 * @return the productions
	 */
	public String getProductions() {
		return productions;
	}

	/**
	 * @param productions
	 *            the productions to set
	 */
	public void setProductions(String productions) {
		this.productions = productions;
	}

	/**
	 * @return the writers
	 */
	public String getWriters() {
		return writers;
	}

	/**
	 * @param writers
	 *            the writers to set
	 */
	public void setWriters(String writers) {
		this.writers = writers;
	}

	/**
	 * @return the singers
	 */
	public String getSingers() {
		return singers;
	}

	/**
	 * @param singers
	 *            the singers to set
	 */
	public void setSingers(String singers) {
		this.singers = singers;
	}

	/**
	 * @return the directors
	 */
	public String getDirectors() {
		return directors;
	}

	/**
	 * @param directors
	 *            the directors to set
	 */
	public void setDirectors(String directors) {
		this.directors = directors;
	}

	/**
	 * @return the casts
	 */
	public String getCasts() {
		return casts;
	}

	/**
	 * @param casts
	 *            the casts to set
	 */
	public void setCasts(String casts) {
		this.casts = casts;
	}
}
