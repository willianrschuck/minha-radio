package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;

public interface Validator<T> {

	void validate(T object) throws InvalidValueException;
	
}
