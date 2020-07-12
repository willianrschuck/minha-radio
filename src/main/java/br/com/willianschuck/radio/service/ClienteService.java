package br.com.willianschuck.radio.service;

import br.com.willianschuck.radio.dao.ClienteDAO;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.validator.ClienteValidator;

public class ClienteService extends Service<Cliente> {

	public ClienteService() {
		super(new ClienteDAO(),  new ClienteValidator());
	}

	@Override
	public Cliente create() {
		
		Cliente cliente = new Cliente();
		cliente.setEndereco(new Endereco());
		return cliente;
	}
	
}
