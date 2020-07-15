package br.com.willianschuck.util;

import static br.com.willianschuck.util.StringUtils.isBlank;

import java.text.DecimalFormat;

public class NumberUtil {

	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.##");

	private NumberUtil() {
	}

	public static Integer asInteger(String s) {
		if (isBlank(s)) {
			return null;
		}
		return Integer.parseInt(s);
	}
	
	public static Double asDouble(String s) {
		if (isBlank(s)) {
			return null;
		}
		return Double.parseDouble(s);
	}
	
	public static String formatInteger(Number n) {
		if (n == null) {
			return "";
		}
		return DECIMAL_FORMAT.format(n);
	}
	
}
