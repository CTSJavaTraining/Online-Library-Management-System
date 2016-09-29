package com.training.blayer;

/**
 * Dto class created to set movie details 
 * 
 * @author 542224
 *
 */
public class MoviesDto {

	private String productions;

	private String writers;

	private String singers;

	private String directors;

	private String casts;

	public String getProductions() {
		return productions;
	}

	public void setProductions(String productions) {
		this.productions = productions;
	}

	public String getWriters() {
		return writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public String getSingers() {
		return singers;
	}

	public void setSingers(String singers) {
		this.singers = singers;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getCasts() {
		return casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

}
