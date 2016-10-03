package com.training.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Dto class created to set music item details
 * 
 * @author 542224
 *
 */
public class MusicDto {

	@NotBlank(message = "productions must not be blank!")
	private String productions;

	@NotBlank(message = "writers must not be blank!")
	private String writers;

	@NotBlank(message = "singers must not be blank!")
	private String singers;

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

}
