package br.com.willianschuck.radio.gui.lista;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.conta.ContaService;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Conta;

public class JPanelContaLista extends JPanelBaseList<Conta> {
	private static final long serialVersionUID = 1L;
	
	private JButton btnImprimir;

	private ContaService contaService;
	
	public JPanelContaLista(Controller controller, ContaService contaService) {
		
		super(controller, "conta_lista", contaService);
		this.contaService = contaService;
		initComponents();
		
	}

	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Valor", "Valor Pago", "Status"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Conta conta : contaService.getAll()) {
			data.add(new Object[] {conta.getId(), conta.getValor(), conta.getValorPago(), conta.getStatus()});
		}
		return data;
		
	}
	
	@Override
	protected void updateToolbarButtons() {
		super.updateToolbarButtons();
		boolean hasItemSelecionado = hasItemSelecionado();
		btnImprimir.setEnabled(hasItemSelecionado);
	}
	
	@Override
	protected String getScreenName() {
		return "Lista de Contas";
	}

}
