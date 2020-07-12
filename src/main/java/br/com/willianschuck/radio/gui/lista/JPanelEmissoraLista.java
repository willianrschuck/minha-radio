package br.com.willianschuck.radio.gui.lista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.service.EmissoraService;

public class JPanelEmissoraLista extends JPanelBaseList<Emissora> {
	private static final long serialVersionUID = 1L;
	private EmissoraService emissoraService;
	
	public JPanelEmissoraLista(Controller controller) {
		
		super(controller, "emissora_lista", controller.getEmissoraService());
		emissoraService = controller.getEmissoraService();
		initComponents();
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Nome Fantasia", "Razão Social", "CNPJ", "Estado", "Cidade", "Logradouro", "Bairro", "Número"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Emissora emissora : emissoraService.getAll()) {
			Endereco endereco = emissora.getEndereco();
			data.add(new Object[] {emissora.getId(), emissora.getNomeFantasia(), emissora.getRazaoSocial(), emissora.getCnpj(), endereco.getCidade().getEstado().toString(), endereco.getCidade().toString(), endereco.getLogradouro(), endereco.getBairro(), endereco.getNumero()});
		}
		return data;
		
	}

}
