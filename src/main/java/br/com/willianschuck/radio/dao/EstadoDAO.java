package br.com.willianschuck.radio.dao;

import br.com.willianschuck.radio.model.Endereco;

public class EstadoDAO extends DAO<Endereco> {

	@Override
	public Class<Endereco> getEntityClass() {
		return Endereco.class;
	}
	
}
