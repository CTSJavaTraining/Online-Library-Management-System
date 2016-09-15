package com.training.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * this class is responsible for LocaldateTime implementations
 * 
 * it implements AttributeConverter to convert date formats from SQL to JAVA
 * 
 * @author 447383
 *
 */

@Converter(autoApply = true)
public class LocalDateTimeUtils implements AttributeConverter<LocalDateTime, Timestamp> {
	/**
	 * this method is used to return system date and time
	 * 
	 * @return
	 */
	public static LocalDateTime getLocalDateTime() {

		return LocalDateTime.now();

	}

	/**
	 * converts java LocalDateTime to Timestamp
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {

		return (attribute == null ? null : Timestamp.valueOf(attribute));
	}

	/**
	 * converts sql Timestamp to java LocalDtaeTime
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp dbData) {

		return (dbData == null ? null : dbData.toLocalDateTime());
	}

}
