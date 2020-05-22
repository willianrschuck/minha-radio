package br.com.willianschuck.radio.dao;

import br.com.willianschuck.radio.model.Emissora;

public class EmissoraDAO extends DAO<Emissora> {

	@Override
	public Class<Emissora> getEntityClass() {
		return Emissora.class;
	}
	
}
