package br.com.willianschuck.radio.cliente;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;

public class ClienteServiceImpl extends AbstractCrudService<Cliente> {

	public ClienteServiceImpl() {
		super(new ClienteDAO());
	}

	@Override
	public Cliente create() {
		
		Cliente cliente = new Cliente();
		cliente.setEndereco(new Endereco());
		return cliente;
	}

}
