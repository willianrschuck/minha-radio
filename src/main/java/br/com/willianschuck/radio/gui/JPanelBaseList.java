package br.com.willianschuck.radio.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.base.CrudService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.model.Entidade;

public abstract class JPanelBaseList<T extends Entidade> extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private JScrollPane scrPaneTable;
	private DefaultTableModel tableModel;
	private JTable table;

	private JButton btnEditar;
	private JButton btnExcluir;
	
	private Actions<T> actions;
	
	private T itemSelecionado;

	private CrudService<T> service;

	private JButton btnNovo;

	public JPanelBaseList(Controller controller, String cardName, CrudService<T> service) {
		super(controller, cardName);
		this.service = service;
		this.initComponents();
	}
	
	protected abstract DefaultTableModel getDefaultTableModel();
	
	protected abstract List<Object[]> getData();
	
	private class ForcedListSelectionModel extends DefaultListSelectionModel {
		private static final long serialVersionUID = 1L;

		public ForcedListSelectionModel () {
	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    }

	    
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		
		tableModel = getDefaultTableModel();
		table = new JTable(tableModel);
		scrPaneTable = new JScrollPane(table);
		scrPaneTable.setBorder(BorderFactory.createEmptyBorder());
		
		addBotoesToolbar();
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setOpaque(false);
		
		table.setSelectionModel(new ForcedListSelectionModel());
		
		table.getTableHeader().setBackground(Colors.getDarkBackgroundColor().brighter());
		table.getTableHeader().setForeground(Color.WHITE);
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
		
		add(scrPaneTable);
		
	}

	private void addBotoesToolbar() {
		
		btnNovo = ComponentFactory.makeButton(Icons.getNewIcon(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO adicionar a��o para criar novo item
			}
		});
		addToolbarItem(btnNovo);
		
		btnEditar = ComponentFactory.makeButton(Icons.getEditIcon(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actions.editar(getItemSelecionado());
			}
		});
		btnEditar.setEnabled(false);
		addToolbarItem(btnEditar);
		
		btnExcluir = ComponentFactory.makeButton(Icons.getDeleteIcon(), new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
		btnExcluir.setEnabled(false);
		addToolbarItem(btnExcluir);
		
	}

	protected void remover() {
		try {
			service.remover(getItemSelecionado());
			tableModel.removeRow(table.getSelectedRow());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void updateToolbarButtons() {
		
		boolean hasItemSelecionado = hasItemSelecionado();
		
		btnEditar.setEnabled(hasItemSelecionado);
		btnExcluir.setEnabled(hasItemSelecionado);
		
	}

	public void editar(T entidade) {
		getActions().editar(entidade);
	}
	
	private T getEntityById(Object id) {
		return service.buscar(id);
	}
	
	public void updateListData() {
		
		tableModel.setNumRows(0);
		for (Object[] o : getData()) {
			tableModel.addRow(o);
		}
		
	}
	
	public boolean hasItemSelecionado() {
		return itemSelecionado != null;
	}
	
	public Actions<T> getActions() {
		return actions;
	}

	public void setActions(Actions<T> actions) {
		this.actions = actions;
	}
	
	public CrudService<T> getService() {
		return service;
	}
	
	public T getItemSelecionado() {
		return itemSelecionado;
	}
	
}
