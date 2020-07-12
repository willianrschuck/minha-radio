package br.com.willianschuck.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private DateUtil() {
	}
	
	private static final DateFormat date = new SimpleDateFormat("dd/MM/yyyy");

	public static Date asDate(String s) {
		try {
			return date.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formatDate(Date d) {
		if (d == null) {
			return "";
		}
		return date.format(d);
	}
	
	public static boolean before(Date a, Date b) {
		return a.before(b);
	}
	
}
