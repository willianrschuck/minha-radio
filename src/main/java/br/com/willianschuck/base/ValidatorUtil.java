package br.com.willianschuck.base;

import java.util.Date;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.util.DateUtil;
import br.com.willianschuck.util.EmailUtil;
import br.com.willianschuck.util.StringUtils;

public class ValidatorUtil {
	
	private static final String MSG_CAMPO_INVALIDO = "O valor %s do campo %s é inválido!";
	private static final String MSG_CAMPO_OBRIGATORIO = "O campo %s é obrigatório!";
	private static final String MSG_MAX_LENGHT = "O campo %s não pode ter mais que %d caracteres!";
	private static final String MSG_MIN_LENGHT = "O campo %s não pode ter menos que %d caracteres!";
	private static final String MSG_OUT_OF_RANGE = "O valor %s do campo %s deve estar entre %s e %s!";

	private ValidatorUtil() {
	}
	
	public static class Email {
		
		private Email() {
		}
		
		public static void validate(String email) throws InvalidValueException {
			if (!EmailUtil.isValid(email)) {
				throw new InvalidValueException(String.format(MSG_CAMPO_INVALIDO, email, "email"));
			}
		}
		
	}
	
	public static void validate(String s, String fieldName, boolean nullable, int maxLenght, int minLenght) throws InvalidValueException {
		
		validate(s, fieldName, nullable, maxLenght);
		
		if (s.length() < minLenght) {
			throw new InvalidValueException(String.format(MSG_MIN_LENGHT, fieldName, minLenght));
		}
		
	}
	
	public static void validate(String s, String fieldName, boolean nullable, int maxLenght) throws InvalidValueException {
		
		validate(s, fieldName, nullable);
		
		if (s.length() > maxLenght) {
			throw new InvalidValueException(String.format(MSG_MAX_LENGHT, fieldName, maxLenght));
		}
		
	}
	
	public static void validate(String s, String fieldName, boolean nullable) throws InvalidValueException {
		
		boolean isBlank = StringUtils.isBlank(s);
		
		if (isBlank) {
			if (nullable) {
				return;
			}
			throw new InvalidValueException(String.format(MSG_CAMPO_OBRIGATORIO, fieldName));
		} 
			
	}
	
	public static void validateRange(Number n, String fieldName, Number min, Number max) throws InvalidValueException {
		
		if (n == null) {
			return;
		}
		
		if (n.doubleValue() < min.doubleValue() || n.doubleValue() > max.doubleValue()) {
			throw new InvalidValueException(String.format(MSG_OUT_OF_RANGE, n, fieldName, min, max));
		}
		
	}
	
	public static void validateDate(Date d, String fieldName, boolean nullable) throws InvalidValueException {
		
		boolean isNull = d == null;
		
		if (isNull && !nullable) {
			throw new InvalidValueException(String.format(MSG_CAMPO_OBRIGATORIO, fieldName));
		}
		
	}
	
	public static void validateRequired(Object o, String fieldName) throws InvalidValueException {
		
		if (o == null) {
			throw new InvalidValueException(String.format(MSG_CAMPO_OBRIGATORIO, fieldName));
		}
			
			
	}

	public static void validateDateRange(Date dataFinal, String string, boolean b, Date dataInicio) throws InvalidValueException {
		
		if (dataFinal.before(dataInicio)) {
			throw new InvalidValueException(String.format("O valor do campo %s n�o pode ser anterior a %s", string, DateUtil.formatDate(dataInicio)));
		}
		
	}
	
}
