package br.com.willianschuck.util;

import java.text.ParseException;

public class PessoaUtil {
	
	private PessoaUtil() {
	}

	public static String formatCnpj(String cnpj) {
		try {
			return Masks.getMaskCnpj().valueToString(cnpj);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
