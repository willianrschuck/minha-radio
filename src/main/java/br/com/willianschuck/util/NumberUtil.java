package br.com.willianschuck.util;

import static br.com.willianschuck.util.StringUtils.isBlank;

public class NumberUtil {

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
	
}
