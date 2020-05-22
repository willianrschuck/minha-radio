package br.com.willianschuck.radio.dao;

import br.com.willianschuck.radio.model.Cliente;

public class ClienteDAO extends DAO<Cliente> {

	@Override
	public Class<Cliente> getEntityClass() {
		return Cliente.class;
	}
	
}
