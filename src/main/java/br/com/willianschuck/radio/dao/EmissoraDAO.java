package br.com.willianschuck.radio.dao;

import java.util.List;

import br.com.willianschuck.radio.model.Emissora;

public class EmissoraDAO extends DAO<Emissora> {

	@Override
	public Class<Emissora> getEntityClass() {
		return Emissora.class;
	}

	public List<Emissora> getAll() {
		return entityManager().createQuery("SELECT e FROM Emissora e", Emissora.class).getResultList();
	}
	
}
