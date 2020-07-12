package br.com.willianschuck.util;

import java.util.regex.Pattern;

public class EmailUtil {
	
	private static final String regex = "^([a-z0-9](?:[._+-]?[a-z0-9])*)@([a-z0-9](?:[._+-]?[a-z0-9])*)$";
	private static final Pattern emailPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); 

	public static boolean isValid(String email) {
		return emailPattern.matcher(email).matches();
	}
	
}
