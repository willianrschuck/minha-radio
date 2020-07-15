package br.com.willianschuck.radio.service;

import java.util.List;

import javax.persistence.RollbackException;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.dao.DAO;
import br.com.willianschuck.radio.model.Entidade;
import br.com.willianschuck.radio.validator.Validator;

public abstract class Service<T extends Entidade> {
	
	private DAO<T> dao;
	private Validator<T> validator;
	
	public Service(DAO<T> dao, Validator<T> validator) {
		this.dao = dao;
		this.validator = validator;
	}

	protected abstract T create();
	
	public void cadastrar(T e) {
		if (e.getId() != null) {
			dao.merge(e);
		} else {
			dao.persist(e);
		}
	}
	
	public void validar(T contrato) throws InvalidValueException {
		validator.validate(contrato);
	}

	public List<T> getAll() {
		return dao.getAll();
	}

	public T getBy(Integer id) {
		return dao.find(id);
	}

	public void remover(T contrato) {
		try {
			dao.remove(contrato);
		} catch (RollbackException e) {
			throw new RollbackException("A exclus�o n�o pode ser conclu�da. Existem outros registros referenciando a entidade selecionada.");
		}
	}
	
	protected DAO<T> getDao() {
		return dao;
	}
	
	protected Validator<T> getValidator() {
		return validator;
	}

}
