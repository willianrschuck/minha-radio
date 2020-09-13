package br.com.willianschuck.base;

public interface ReportService<T> {

	byte[] makeReport(T object);
	
}
