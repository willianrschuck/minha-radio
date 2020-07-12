package br.com.willianschuck.radio.gui.lista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.service.ClienteService;

public class JPanelClienteLista extends JPanelBaseList<Cliente> {
	private static final long serialVersionUID = 1L;
	private ClienteService clienteService;
	
	public JPanelClienteLista(Controller controller) {
		
		super(controller, "cliente_lista", controller.getClienteService());
		clienteService = controller.getClienteService();
		initComponents();
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Nome", "Telefone", "E-mail", "Estado", "Cidade", "Logradouro", "Bairro", "Número"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Cliente cliente : clienteService.getAll()) {
			Endereco endereco = cliente.getEndereco();
			data.add(new Object[] {cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), endereco.getCidade().getEstado().toString(), endereco.getCidade().toString(), endereco.getLogradouro(), endereco.getBairro(), endereco.getNumero()});
		}
		return data;
		
	}

}
