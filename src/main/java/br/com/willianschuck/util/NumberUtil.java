package br.com.willianschuck.util;

import static br.com.willianschuck.util.StringUtils.isBlank;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtil {

	public static final DecimalFormat DECIMAL = new DecimalFormat("#,##0.##");
	public static final DecimalFormat CURRENCY = new DecimalFormat("#,##0.00");

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
		return Double.valueOf(s);
	}
	
	public static String formatInteger(Number n) {
		if (n == null) {
			return "";
		}
		return DECIMAL.format(n);
	}
	
	public static String formatCurrency(Number n) {
		if (n == null) {
			return "";
		}
		return CURRENCY.format(n);
	}
	
	public static Double fromCurrency(String s) {
		if (isBlank(s)) {
			return 0d;
		}
		try {
			return CURRENCY.parse(s).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0d;
		}
	}
	
}
