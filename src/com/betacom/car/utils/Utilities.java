package com.betacom.car.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import com.betacom.car.exception.AcademyException;


public class Utilities {
	
	public static String dateToString(String pattern, LocalDate myDate) {
		
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
		
		return myDate.format(formatter);
		
	}
	
	public static String dateToString(String pattern, LocalDateTime myDate) {
		
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
		
		return myDate.format(formatter);
		
	}
	
	public static LocalDate stringToDate (String pattern, String myDate) throws AcademyException{
		LocalDate r=null;
		
		try {
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
			
			r=LocalDate.parse(myDate, formatter);
		} catch (DateTimeParseException e) {
			throw new AcademyException ("Formato della data invalida: "+myDate);
		}

		return r;
	}


}
