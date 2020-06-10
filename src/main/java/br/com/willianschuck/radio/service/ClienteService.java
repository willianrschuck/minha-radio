package br.com.willianschuck.radio.service;

import java.util.List;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.dao.ClienteDAO;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.validator.ClienteValidator;

public class ClienteService {

	ClienteDAO dao = new ClienteDAO();
	ClienteValidator validator = new ClienteValidator();
	
	public void cadastrar(Cliente cliente) {
		dao.persist(cliente);
	}
	
	public void validar(Cliente cliente) throws InvalidValueException {
		validator.validate(cliente);
	}

	public List<Cliente> getAll() {
		return dao.getAll();
	}

	public Cliente getBy(Integer id) {
		return dao.find(id);
	}

	public void remover(Cliente cliente) {
		dao.remove(cliente);
	}
	
}
