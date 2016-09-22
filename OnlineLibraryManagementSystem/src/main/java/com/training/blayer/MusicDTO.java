package com.training.blayer;

/**
 * 
 * @author 447482
 *
 */
public class MusicDTO extends LibraryItemsDTO {

	private String productions;

	private String writers;

	private String singers;

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

}
