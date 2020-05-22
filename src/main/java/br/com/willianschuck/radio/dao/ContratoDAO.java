package br.com.willianschuck.radio.dao;

import br.com.willianschuck.radio.model.Contrato;

public class ContratoDAO extends DAO<Contrato> {

	@Override
	public Class<Contrato> getEntityClass() {
		return Contrato.class;
	}
	
}
