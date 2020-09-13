package br.com.willianschuck.util;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public final class Masks {

	private static MaskFormatter maskCnpj;
	private static MaskFormatter maskCpf;
	
	private Masks() {
	}
	
	public static MaskFormatter getMaskCnpj() {
		if (maskCnpj == null) {
			try {
				maskCnpj = new MaskFormatter("##.###.###/####-##");
				maskCnpj.setPlaceholderCharacter('_');
				maskCnpj.setValueContainsLiteralCharacters(false);
				maskCnpj.setCommitsOnValidEdit(false);
			} catch (Exception e) {
				e.printStackTrace();
				maskCnpj = null;
			}
		}
		return maskCnpj;
	}
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(getMaskCpf().stringToValue("123.456.789-0"));

	}
	
	public static MaskFormatter getMaskCpf() {
		if (maskCpf == null) {
			try {
				maskCpf = new MaskFormatter("###.###.###-##");
				maskCpf.setPlaceholderCharacter('_');
				maskCpf.setValueContainsLiteralCharacters(false);
//				maskCpf.setCommitsOnValidEdit(false);
				maskCpf.setOverwriteMode(true);
			} catch (Exception e) {
				e.printStackTrace();
				maskCpf = null;
			}
		}
		return maskCpf;
	}
	
}
