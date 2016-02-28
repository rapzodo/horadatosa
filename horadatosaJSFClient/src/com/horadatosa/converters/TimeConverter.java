package com.horadatosa.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatterBuilder;

@FacesConverter(value="timeConverter")
public class TimeConverter implements Converter {

	private static final String TIME_FORMAT = "HH:mm";

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String value) {
		try {
			DateTimeFormatterBuilder formater = new DateTimeFormatterBuilder().appendPattern(TIME_FORMAT);
			return LocalTime.parse(value, formater.toFormatter());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext cxt, UIComponent comp, Object value) {
		SimpleDateFormat df = new SimpleDateFormat(TIME_FORMAT);
		if(value instanceof Date){
			return df.format(value);
		}
		return null;
	}

}
