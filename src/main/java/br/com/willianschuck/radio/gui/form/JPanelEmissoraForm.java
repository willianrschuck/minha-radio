package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.control.Actions;
import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBase;
import br.com.willianschuck.radio.model.Emissora;

public class JPanelEmissoraForm extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private Emissora emissora;
	
	private JLabel lblNomeFantasia;
	private JLabel lblRazaoSocial;
	private JLabel lblCnpj;
	
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	
	private JPanel pnlForm;

	private Actions<Emissora> actions;

	private JPanel dadosEmissora;

	private JPanelEnderecoForm enderecoForm;

	private JButton btnSalvar;

	public JPanelEmissoraForm(Controller controller) {
		super(controller, "emissora_form");
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());
		
		lblNomeFantasia = new JLabel("Nome Fantasia ", SwingConstants.RIGHT);
		lblRazaoSocial = new JLabel("Razão Social ", SwingConstants.RIGHT);
		lblCnpj = new JLabel("Nome ", SwingConstants.RIGHT);
		
		txtNomeFantasia = new JTextField();
		txtRazaoSocial = new JTextField();
		txtCnpj = new JTextField();
		
		dadosEmissora = new JPanel(new GridBagLayout());

		addToPanel(dadosEmissora, lblNomeFantasia, 0, 0, 1);
		addToPanel(dadosEmissora, txtNomeFantasia, 1, 0, 1, 1, 0);
		addToPanel(dadosEmissora, lblRazaoSocial, 0, 1, 1);
		addToPanel(dadosEmissora, txtRazaoSocial, 1, 1, 1, 1, 0);
		addToPanel(dadosEmissora, lblCnpj, 0, 2, 1);
		addToPanel(dadosEmissora, txtCnpj, 1, 2, 1, 1, 0);

		enderecoForm = new JPanelEnderecoForm(controller());
		
		addToPanel(pnlForm, dadosEmissora, 0, 0, 1, 1, 0);
		addToPanel(pnlForm, enderecoForm, 0, 1, 1, 1, 0);
		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		addToolbarItem(btnSalvar);
		
		setContent(pnlForm);
		
	}
	
	private void salvar() {
		
		preencherObjeto();
		
		try {
			controller().getEmissoraService().validar(emissora);
			controller().getEmissoraService().cadastrar(emissora);
			actions.listar();
		} catch (InvalidValueException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro durante o cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void editar(Emissora emissora) {
		this.emissora = emissora;
		preencherCampos();
	}

	private void preencherCampos() {
		
		txtNomeFantasia.setText(emissora.getNomeFantasia());
		txtRazaoSocial.setText(emissora.getRazaoSocial());
		txtCnpj.setText(emissora.getCnpj());
		enderecoForm.editar(emissora.getEndereco());
		
	}
	
	private void preencherObjeto() {
		
		emissora.setNomeFantasia(txtNomeFantasia.getText());
		emissora.setRazaoSocial(txtRazaoSocial.getText());
		emissora.setCnpj(txtCnpj.getText());
		emissora.setEndereco(enderecoForm.getEndereco());
		
	}

	public void setEmissoraActions(Actions<Emissora> actions) {
		this.actions = actions;
	}
	
}
