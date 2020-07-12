package br.com.willianschuck.radio.gui.lista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.service.ContratoService;

public class JPanelContratoLista extends JPanelBaseList<Contrato> {
	private static final long serialVersionUID = 1L;
	
	private ContratoService contratoService;
	
	public JPanelContratoLista(Controller controller) {
		
		super(controller, "contrato_lista", controller.getContratoService());
		contratoService = controller.getContratoService();
		initComponents();
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Cliente", "Início", "Término", "Valor Mensal"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Contrato contrato : contratoService.getAll()) {
			data.add(new Object[] {contrato.getId(), contrato.getCliente().getNome(), contrato.getDataInicio(), contrato.getDataFinal(), contrato.getPrecoMensal()});
		}
		return data;
		
	}

}
