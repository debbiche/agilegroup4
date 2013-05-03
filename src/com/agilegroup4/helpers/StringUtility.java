package com.agilegroup4.helpers;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.Html;

/* 
 * Utility class for string methods. E.g. Removing backslashes from a string.
 */
@SuppressLint("SimpleDateFormat")
public class StringUtility {

	
	/* 
	 * Converts a string containing HTML tags to a string readable for the android UI
	 * @param html The string containing html tags.
	 * @return A string without html tags.
	 */
	public static String convertHTMLtoString (String html) {
		
		final String parsedHtml = removeBackslashNFromString(html) ;
		return Html.fromHtml(parsedHtml).toString();
	}
	
	/* 
	 * Removes backslash from string "\\n"
	 * @param input The string containing backslash tags.
	 * @return A string without backslash tags. 
	 */
	public static String removeBackslashNFromString(String input){
		return input.replace("\\n", "");
	}

	/* 
	 * Converts a string into a Date object in format yyyy-MM-dd
	 * @param date The string you want to convert
	 * @return A date object parsed from the provided string. 
	 */
	public static Date stringToDate(String date) {

		DateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return newDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
