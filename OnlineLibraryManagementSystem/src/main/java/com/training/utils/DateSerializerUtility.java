package com.training.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * this class is used to serialize Java.util.date using the serialize
 * method
 * 
 * @author 542224
 *
 */
public class DateSerializerUtility extends JsonSerializer<Date> {
	
	

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}

}
