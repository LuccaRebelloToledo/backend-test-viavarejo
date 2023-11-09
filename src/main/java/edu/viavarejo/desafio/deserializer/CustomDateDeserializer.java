package edu.viavarejo.desafio.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateDeserializer implements JsonDeserializer<Date> {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws com.google.gson.JsonParseException {
		try {
			return dateFormat.parse(json.getAsString());
		} catch (ParseException e) {
			throw new com.google.gson.JsonParseException(e);
		}
	}
}
