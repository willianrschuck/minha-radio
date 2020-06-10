package br.com.willianschuck.radio.gui.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBase;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.service.ClienteService;

public class JPanelClienteLista extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private JScrollPane scrPaneTable;
	private JTable table;
	private JButton btnEditar;

	private JPanelClienteForm pClienteForm;

	private ClienteService clienteService;

	private JButton btnExcluir;

	public JPanelClienteLista(Controller controller, JPanelClienteForm pClienteForm) {
		
		super(controller);
		
		this.pClienteForm = pClienteForm;
		this.clienteService = controller().getClienteService();
		
		initComponents();
		
	}
	
	@Override
	protected void initComponents() {
		
		super.initComponents();

		criarItensToolbar();
		criarPanelTabela();

		setContentPane(scrPaneTable);
		
		popularTabela();
		
	}
	
	public void popularTabela() {
		
		tableModel.setNumRows(0);
		List<Cliente> clientes = clienteService.getAll();
		
		for (Cliente cliente : clientes) {
			Endereco endereco = cliente.getEndereco();
			tableModel.addRow(new Object[] {cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getEmail(), endereco.getCidade().getEstado().toString(), endereco.getCidade().toString(), endereco.getLogradouro(), endereco.getBairro(), endereco.getNumero()});
		}
		
	}
	
	private Cliente getClienteSelecionado() {
	
		Integer clientId = (Integer) table.getValueAt(table.getSelectedRow(), 0);
		return controller().getClienteService().getBy(clientId);
		
	}

	private void criarPanelTabela() {
		
		tableModel = new DefaultTableModel(new Object[] {"Cód.", "Nome", "Telefone", "E-mail", "Estado", "Cidade", "Logradouro", "Bairro", "Número"}, 0);
		table = new JTable(tableModel);
		scrPaneTable = new JScrollPane(table);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				updateToolbarButtons();
			}
		});
		updateToolbarButtons();
		
	}

	private void criarItensToolbar() {
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pClienteForm.editar(getClienteSelecionado());
			}
		});
		addToolbarItem(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller().getClienteService().remover(getClienteSelecionado());
				tableModel.removeRow(table.getSelectedRow());
			}
		});
		addToolbarItem(btnExcluir);
		
	}

	private void updateToolbarButtons() {
		if (table.getSelectedRow() == -1) {
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);
		} else {
			btnEditar.setEnabled(true);
			btnExcluir.setEnabled(true);
		}
	}
	
}
