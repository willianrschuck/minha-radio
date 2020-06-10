package br.com.willianschuck.radio.gui.estado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.dao.EstadoDAO;
import br.com.willianschuck.radio.model.Estado;

public class JPanelListagemEstado extends JPanel { 
	private static final long serialVersionUID = 1L;
	
	
	private EstadoDAO estadoDao = new EstadoDAO();
	
	private BorderLayout borderLayout;
	private JScrollPane scrollPaneTabela;
	private DefaultTableModel tableModel;
	private JTable tableEstados;

	public JPanelListagemEstado(Controller controller) {
		initComponents();
	}

	private void initComponents() {

		borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		tableModel = new DefaultTableModel(new Object[] {"Id", "Nome"}, 0);
		tableEstados = new JTable(tableModel);
		
		scrollPaneTabela = new JScrollPane(tableEstados);
		add(scrollPaneTabela);
		setBackground(Color.BLACK);
		
		populateTable();
		
	}
	
	public void populateTable() {
		
		tableModel.setNumRows(0);

		List<Estado> estados = estadoDao.getAll();

		for (Estado estado : estados) {
			tableModel.addRow(new Object[] {estado.getId(), estado.getNome()});
		}
		
	}
	
}
