package br.com.willianschuck.util;

public class ObjectUtil {

	private ObjectUtil() {
	}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static String getAsString(Object o) {
		if (isNull(o)) {
			return "";
		}
		return o.toString();
	}
	
}
