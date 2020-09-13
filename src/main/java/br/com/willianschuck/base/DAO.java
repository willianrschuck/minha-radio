package br.com.willianschuck.base;

public interface DAO<T> {

	void cadastrar(T o);
	
	T atualizar(T o);
	
	T buscar(Object id);
	
	void remover(T o);
	
}
