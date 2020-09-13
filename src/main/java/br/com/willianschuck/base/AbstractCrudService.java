package br.com.willianschuck.base;

import java.util.List;

import javax.persistence.RollbackException;

public abstract class AbstractCrudService<T> implements CrudService<T> {
	
	private AbstractDAO<T> dao;
	
	public AbstractCrudService(AbstractDAO<T> dao) {
		this.dao = dao;
	}

	public abstract T create();
	
	public void cadastrar(T e) {
		dao.cadastrar(e);
	}
	
	public T atualizar(T e) {
		return dao.atualizar(e);
	}
	
	public List<T> getAll() {
		return dao.getAll();
	}

	public T buscar(Object id) {
		return dao.buscar(id);
	}

	public void remover(T contrato) {
		try {
			dao.remover(contrato);
		} catch (RollbackException e) {
			throw new RollbackException("A exclusão não pode ser concluída. Existem outros registros referenciando a entidade selecionada.");
		}
	}

}
