package br.com.willianschuck.radio.dao;

import java.util.List;

import br.com.willianschuck.radio.model.Estado;

public class EstadoDAO extends DAO<Estado> {

	@Override
	public Class<Estado> getEntityClass() {
		return Estado.class;
	}
	
	public List<Estado> getAll() {
		return super.entityManager().createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
	}
	
}
