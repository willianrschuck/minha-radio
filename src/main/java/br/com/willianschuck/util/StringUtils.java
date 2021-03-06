package br.com.willianschuck.util;

public class StringUtils {
	
	private StringUtils() {
	}
	
	public static boolean isNull(String s) {
		return s == null;
	}
	
	public static boolean isEmpty(String s) {
		return isNull(s) || s.isEmpty();
	}
	
	public static boolean isBlank(String s) {
		return isNull(s) || isEmpty(s);
	}
	
	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}
	
	public static boolean validateLenght(String s, int minLenght, int maxLenght) {
		return !isNull(s) && s.length() >= minLenght && s.length() <= maxLenght;
	}
	
}
