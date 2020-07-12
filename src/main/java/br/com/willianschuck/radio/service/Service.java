package br.com.willianschuck.radio.service;

import java.util.List;

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
		dao.remove(contrato);
	}
	
	protected DAO<T> getDao() {
		return dao;
	}
	
	protected Validator<T> getValidator() {
		return validator;
	}

}
