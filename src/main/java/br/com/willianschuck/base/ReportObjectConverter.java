package br.com.willianschuck.base;

public interface ReportObjectConverter<T, E> {

	E convert(T object);
	
}
