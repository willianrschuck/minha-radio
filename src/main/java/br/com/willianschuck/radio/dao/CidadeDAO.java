package br.com.willianschuck.radio.dao;

import br.com.willianschuck.radio.model.Cidade;

public class CidadeDAO extends DAO<Cidade> {

	@Override
	public Class<Cidade> getEntityClass() {
		return Cidade.class;
	}
	
}
