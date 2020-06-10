package br.com.willianschuck.exception;

public class InvalidValueException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidValueException() {
	}
	
	public InvalidValueException(String message) {
		super(message);
	}
	
}
