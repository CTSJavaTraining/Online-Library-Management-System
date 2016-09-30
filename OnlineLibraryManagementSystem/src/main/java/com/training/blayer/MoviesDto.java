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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((casts == null) ? 0 : casts.hashCode());
		result = prime * result + ((directors == null) ? 0 : directors.hashCode());
		result = prime * result + ((productions == null) ? 0 : productions.hashCode());
		result = prime * result + ((singers == null) ? 0 : singers.hashCode());
		result = prime * result + ((writers == null) ? 0 : writers.hashCode());
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
		MoviesDto other = (MoviesDto) obj;
		if (casts == null) {
			if (other.casts != null)
				return false;
		} else if (!casts.equals(other.casts))
			return false;
		if (directors == null) {
			if (other.directors != null)
				return false;
		} else if (!directors.equals(other.directors))
			return false;
		if (productions == null) {
			if (other.productions != null)
				return false;
		} else if (!productions.equals(other.productions))
			return false;
		if (singers == null) {
			if (other.singers != null)
				return false;
		} else if (!singers.equals(other.singers))
			return false;
		if (writers == null) {
			if (other.writers != null)
				return false;
		} else if (!writers.equals(other.writers))
			return false;
		return true;
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
