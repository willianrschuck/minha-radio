package br.com.willianschuck.radio.dao;

import java.util.List;

import br.com.willianschuck.radio.model.Cliente;

public class ClienteDAO extends DAO<Cliente> {

	@Override
	public Class<Cliente> getEntityClass() {
		return Cliente.class;
	}

	public List<Cliente> getAll() {
		return entityManager().createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
	}
	
}
