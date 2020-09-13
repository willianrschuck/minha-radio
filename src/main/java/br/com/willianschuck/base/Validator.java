package br.com.willianschuck.base;

import br.com.willianschuck.exception.InvalidValueException;

public interface Validator<T> {

	void validate(T object) throws InvalidValueException;
	
}
