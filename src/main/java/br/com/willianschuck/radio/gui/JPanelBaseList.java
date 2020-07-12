package br.com.willianschuck.radio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Actions;
import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.model.Entidade;
import br.com.willianschuck.radio.service.Service;

public abstract class JPanelBaseList<T extends Entidade> extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrPaneTable;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnEditar;
	private JButton btnExcluir;
	
	private Actions<T> actions;
	
	private T itemSelecionado;

	private Service<T> service;

	public JPanelBaseList(Controller controller, String cardName, Service<T> service) {
		super(controller, cardName);
		this.service = service;
		this.initComponents();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		
		tableModel = getDefaultTableModel();
		table = new JTable(tableModel);
		scrPaneTable = new JScrollPane(table);
		
		addBotoesToolbar();
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				itemSelecionado = null;
				if (selectedRowIndex > -1) {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					itemSelecionado = getEntityById(id);
				}
				updateToolbarButtons();
			}
		});
		updateToolbarButtons();
		
		add(scrPaneTable);
		
	}

	private void addBotoesToolbar() {

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.editar(getItemSelecionado());
			}
		});
		addToolbarItem(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.removeRow(table.getSelectedRow());
			}
		});
		addToolbarItem(btnExcluir);
		
	}

	protected void updateToolbarButtons() {
		
		boolean hasItemSelecionado = hasItemSelecionado();
		
		btnEditar.setEnabled(hasItemSelecionado);
		btnExcluir.setEnabled(hasItemSelecionado);
		
	}

	public void editar(T entidade) {
		getActions().editar(entidade);
	}
	
	private T getEntityById(Integer id) {
		return service.getBy(id);
	}
	
	public void updateListData() {
		
		tableModel.setNumRows(0);
		for (Object[] o : getData()) {
			tableModel.addRow(o);
		}
		
	}
	
	protected abstract DefaultTableModel getDefaultTableModel();
	
	protected abstract List<Object[]> getData();
	
	public boolean hasItemSelecionado() {
		return itemSelecionado != null;
	}
	
	public Actions<T> getActions() {
		return actions;
	}

	public void setActions(Actions<T> actions) {
		this.actions = actions;
	}
	
	public Service<T> getService() {
		return service;
	}
	
	public T getItemSelecionado() {
		return itemSelecionado;
	}
	
}
