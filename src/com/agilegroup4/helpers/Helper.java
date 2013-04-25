package com.agilegroup4.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.Html;

@SuppressLint("SimpleDateFormat")
public class Helper {

	public Helper() {

	}

	/*
	 * converts a string containing HTML tags to a string readable for the
	 * android UI
	 */
	public String convertHTMLtoString(String html) {

		String parsedHtml = removeBackslashNFromString(html);
		return Html.fromHtml(parsedHtml).toString();
	}

	public String removeBackslashNFromString(String input) {
		return input.replace("\\n", "");
	}

	public static Date stringToDate(String date) {

		DateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return newDate.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

}
