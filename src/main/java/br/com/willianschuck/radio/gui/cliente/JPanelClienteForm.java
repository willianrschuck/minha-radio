package br.com.willianschuck.radio.gui.cliente;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.Cards;
import br.com.willianschuck.radio.gui.JPanelBase;
import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.model.Estado;

public class JPanelClienteForm extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	// ---- Componentes do Formulário

	// Atributos do Cliente
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	
	// Atributos do Endereço do Cliente
	private JLabel lblLogradouro;
	private JLabel lblNumero;
	private JLabel lblBairro;
	private JLabel lblEstado;
	private JLabel lblCidade;
	
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JComboBox<Estado> cbxEstado;
	private JComboBox<Cidade> cbxCidade;
	
	private JButton btnSalvar;

	private GridBagConstraints gbc;

	private JPanel pnlForm;

	public JPanelClienteForm(Controller controller) {
		
		super(controller);
		initComponents();
		
	}

	@Override
	protected void initComponents() {
		
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());
		
		// Campos pessoais 
		txtNome = new JTextField();
		txtTelefone = new JTextField();
		txtEmail = new JTextField();
		
		// Campos do endereço
		txtLogradouro = new JTextField();
		txtNumero = new JTextField();
		txtBairro = new JTextField();
		cbxEstado = new JComboBox<Estado>();
		cbxCidade = new JComboBox<Cidade>();
		
		cbxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popularCbxCidade();
			}
		});
		cbxCidade.setEnabled(false);
		
		popularCbxEstado();
		
		lblNome = new JLabel("Nome ", SwingConstants.RIGHT);
		lblTelefone = new JLabel("Telefone ", SwingConstants.RIGHT);
		lblEmail = new JLabel("E-mail ", SwingConstants.RIGHT);
		
		lblLogradouro = new JLabel("Logradouro ", SwingConstants.RIGHT);
		lblNumero = new JLabel("Numero ", SwingConstants.RIGHT);
		lblBairro = new JLabel("Bairro ", SwingConstants.RIGHT);
		lblCidade = new JLabel("Cidade ", SwingConstants.RIGHT);
		lblEstado = new JLabel("Estado ", SwingConstants.RIGHT);
		
		
		gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 10;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;
		
		JPanel infosPessoais = new JPanel(new GridBagLayout());
		JPanel infosEndereco = new JPanel(new GridBagLayout());
		infosEndereco.setBorder(new CompoundBorder(new TitledBorder("Endereço"), new EmptyBorder(0, 0, 0, 0)));
		
		addToPanel(infosPessoais, lblNome, 0, 0, 3);
		addToPanel(infosPessoais, txtNome, 3, 0, 9, 1, 0);
		
		addToPanel(infosPessoais, lblTelefone, 0, 1, 3);
		addToPanel(infosPessoais, txtTelefone, 3, 1, 3, 1, 0);
		addToPanel(infosPessoais, lblEmail, 6, 1, 3);
		addToPanel(infosPessoais, txtEmail, 9, 1, 3, 1, 0);
	
		
		addToPanel(infosEndereco, lblEstado, 0, 0, 1);
		addToPanel(infosEndereco, cbxEstado, 1, 0, 1, 1, 0);
		
		addToPanel(infosEndereco, lblCidade, 0, 1, 1);
		addToPanel(infosEndereco, cbxCidade, 1, 1, 1, 1, 0);
		
		addToPanel(infosEndereco, lblLogradouro, 0, 2, 1);
		addToPanel(infosEndereco, txtLogradouro, 1, 2, 3, 1, 0);
		
		addToPanel(infosEndereco, lblBairro, 0, 3, 1);
		addToPanel(infosEndereco, txtBairro, 1, 3, 1, 1, 0);
		
		addToPanel(infosEndereco, lblNumero, 0, 4, 1);
		addToPanel(infosEndereco, txtNumero, 1, 4, 1, 1, 0);

		gbc.insets = new Insets(0, 0, 0, 0);
		addToPanel(pnlForm, infosPessoais, 0, 0, 1, 1, 0);
		
		gbc.insets = new Insets(3, 3, 3, 3);
		
		addToPanel(pnlForm, infosEndereco, 0, 1, 1, 1, 0);
		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);
		

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		
		addToolbarItem(btnSalvar);
		setContentPane(pnlForm);
		
	}

	private void getClienteFromFormData() {
		
		if (cliente == null) {
			cliente = new Cliente();
		}
		
		String nome = txtNome.getText();
		String telefone = txtTelefone.getText();
		String email = txtEmail.getText();
		
		String logradouro = txtLogradouro.getText();
		String numero = txtNumero.getText();
		String bairro = txtBairro.getText();
		Cidade cidade = (Cidade) cbxCidade.getSelectedItem();
		
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setEmail(email);

		Endereco endereco = new Endereco();
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		
		cliente.setEndereco(endereco);
		cliente.setDataCadastro(new Date());
		
	}
	
	private void salvar() {
		
		getClienteFromFormData();
		
		try {
			controller().getClienteService().validar(cliente);
			controller().getClienteService().cadastrar(cliente);
		} catch (InvalidValueException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro durante o cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void loadFromObject() {
		
		txtNome.setText(cliente.getNome());
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());;
		
		Endereco endereco = cliente.getEndereco();
		
		txtLogradouro.setText(endereco.getLogradouro());
		txtNumero.setText(endereco.getNumero());
		txtBairro.setText(endereco.getBairro());
		cbxEstado.setSelectedItem(endereco.getCidade().getEstado());
		cbxCidade.setSelectedItem(endereco.getCidade());

	}
	
	private void popularCbxEstado() {
		
		Estado padrao = new Estado();
		padrao.setNome("Selecione...");
		
		List<Estado> estados = controller().getEstadoDao().getAll();
		
		cbxEstado.addItem(padrao);
		for (Estado estado : estados) {
			cbxEstado.addItem(estado);
		}
		
		cbxEstado.setSelectedIndex(0);
		
	}

	private void popularCbxCidade() {
		
		Cidade optPadrao = new Cidade();
		optPadrao.setNome("Selecione...");
		
		Estado estado = (Estado) cbxEstado.getSelectedItem();

		cbxCidade.removeAllItems();
		cbxCidade.addItem(optPadrao);
		
		if (estado != null && estado.getId() != null) {
			
			List<Cidade> cidades = controller().getCidadeDao().getFrom(estado);
			for (Cidade cidade : cidades) {
				cbxCidade.addItem(cidade);
			}
			cbxCidade.setEnabled(true);
			
		} else {
			
			cbxCidade.setEnabled(false);
			
		}
		
	}

	public void editar(Cliente cliente) {
		
		this.cliente = cliente;
		loadFromObject();
		controller().viewCard(Cards.CLIENTE_FORM);
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	private void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan, int weightx, int weighty) {
		
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = colspan;
		
		panel.add(component, gbc);
		
	}
	
	private void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan) {
		addToPanel(panel, component, x, y, colspan, 0, 0);
	}
	
}
