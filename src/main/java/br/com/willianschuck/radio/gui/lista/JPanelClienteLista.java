package br.com.willianschuck.radio.gui.lista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.gui.UneditableDefaultTableModel;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;

public class JPanelClienteLista extends JPanelBaseList<Cliente> {
	private static final long serialVersionUID = 1L;
	private AbstractCrudService<Cliente> clienteService;
	
	public JPanelClienteLista(Controller controller, AbstractCrudService<Cliente> clienteService) {
		
		super(controller, "cliente_lista", clienteService);
		this.clienteService = clienteService;
		initComponents();
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new UneditableDefaultTableModel(new Object[] {"Cód.", "Nome", "Telefone", "E-mail", "Estado", "Cidade", "Logradouro", "Bairro", "Número"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Cliente cliente : clienteService.getAll()) {
			Endereco endereco = cliente.getEndereco();
			data.add(new Object[] {cliente.getId(), cliente.getNomeFantasia(), cliente.getTelefone(), cliente.getEmail(), endereco.getCidade().getEstado().toString(), endereco.getCidade().toString(), endereco.getLogradouro(), endereco.getBairro(), endereco.getNumero()});
		}
		return data;
		
	}
	
	@Override
	protected String getScreenName() {
		return "Lista de Clientes";
	}

}
