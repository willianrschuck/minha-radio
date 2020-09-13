package br.com.willianschuck.base;

public interface CrudService<T> {

	void cadastrar(T e);
	
	T atualizar(T e);
	
	void remover(T e);
	
	T buscar(Object id);
	
}
