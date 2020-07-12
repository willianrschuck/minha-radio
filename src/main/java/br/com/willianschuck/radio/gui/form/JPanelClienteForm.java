package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseForm;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.service.ClienteService;

public class JPanelClienteForm extends JPanelBaseForm<Cliente> {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;

	private JPanel pnlForm;

	private JPanel infosPessoais;
	private JPanelEnderecoForm enderecoForm;

	public JPanelClienteForm(Controller controller) {
		
		super(controller, "cliente_form", new ClienteService());
		initComponents();
		
	}

	@Override
	protected void initComponents() {
		
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());
		
		txtNome = new JTextField();
		txtTelefone = new JTextField();
		txtEmail = new JTextField();
		
		lblNome = new JLabel("Nome ", SwingConstants.RIGHT);
		lblTelefone = new JLabel("Telefone ", SwingConstants.RIGHT);
		lblEmail = new JLabel("E-mail ", SwingConstants.RIGHT);
		
		infosPessoais = new JPanel(new GridBagLayout());
		
		addToPanel(infosPessoais, lblNome, 0, 0, 3);
		addToPanel(infosPessoais, txtNome, 3, 0, 9, 1, 0);
		addToPanel(infosPessoais, lblTelefone, 0, 1, 3);
		addToPanel(infosPessoais, txtTelefone, 3, 1, 3, 1, 0);
		addToPanel(infosPessoais, lblEmail, 6, 1, 3);
		addToPanel(infosPessoais, txtEmail, 9, 1, 3, 1, 0);
	
		enderecoForm = new JPanelEnderecoForm(controller());
		
		addToPanel(pnlForm, infosPessoais, 0, 0, 1, 1, 0);
		addToPanel(pnlForm, enderecoForm, 0, 1, 1, 1, 0);
		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);

		setContent(pnlForm);
		
	}
	
	@Override
	protected Cliente montarObjeto(Cliente cliente) {
		
		if (cliente == null) {
			cliente = controller().getClienteService().create();
		}
		
		cliente.setNome(txtNome.getText());
		cliente.setTelefone(txtTelefone.getText());
		cliente.setEmail(txtEmail.getText());

		cliente.setEndereco(enderecoForm.getEndereco());
		
		if (cliente.getDataCadastro() == null) {
			cliente.setDataCadastro(new Date());
		}
		
		return cliente;
		
	}

	@Override
	protected void preencherFormulario(Cliente cliente) {
		
		txtNome.setText(cliente.getNome());
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
		
		enderecoForm.editar(cliente.getEndereco());
		
	}
	
}
