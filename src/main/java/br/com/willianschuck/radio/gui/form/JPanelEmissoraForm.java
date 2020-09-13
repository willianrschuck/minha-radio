package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.GridBagLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.endereco.EnderecoService;
import br.com.willianschuck.radio.gui.JPanelBaseForm;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.util.Masks;

public class JPanelEmissoraForm extends JPanelBaseForm<Emissora> {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNomeFantasia;
	private JLabel lblRazaoSocial;
	private JLabel lblCnpj;
	
	private JTextField txtNomeFantasia;
	private JTextField txtRazaoSocial;
	private JFormattedTextField txtCnpj;
	
	private JPanel pnlForm;
	private JPanel dadosEmissora;
	private JPanelEnderecoForm enderecoForm;

	private EnderecoService enderecoService;

	public JPanelEmissoraForm(Controller controller, AbstractCrudService<Emissora> emissoraService, EnderecoService enderecoService) {
		super(controller, "emissora_form", emissoraService);
		this.enderecoService = enderecoService;
		initComponents();
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());
		
		lblNomeFantasia = new JLabel("Nome Fantasia ", SwingConstants.RIGHT);
		lblRazaoSocial = new JLabel("Razão Social ", SwingConstants.RIGHT);
		lblCnpj = new JLabel("CNPJ ", SwingConstants.RIGHT);
		
		txtNomeFantasia = new JTextField();
		txtRazaoSocial = new JTextField();
		txtCnpj = new JFormattedTextField(Masks.getMaskCnpj());
		
		dadosEmissora = new JPanel(new GridBagLayout());

		addToPanel(dadosEmissora, lblNomeFantasia, 0, 0, 1);
		addToPanel(dadosEmissora, txtNomeFantasia, 1, 0, 1, 1, 0);
		addToPanel(dadosEmissora, lblRazaoSocial, 0, 1, 1);
		addToPanel(dadosEmissora, txtRazaoSocial, 1, 1, 1, 1, 0);
		addToPanel(dadosEmissora, lblCnpj, 0, 2, 1);
		addToPanel(dadosEmissora, txtCnpj, 1, 2, 1, 1, 0);

		enderecoForm = new JPanelEnderecoForm(enderecoService);
		
		addToPanel(pnlForm, dadosEmissora, 0, 0, 1, 1, 0);
		addToPanel(pnlForm, enderecoForm, 0, 1, 1, 1, 0);
		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);
		
		setContent(pnlForm);
		
	}

	@Override
	protected Emissora montarObjeto(Emissora emissora) {
		
		emissora.setNomeFantasia(txtNomeFantasia.getText());
		emissora.setRazaoSocial(txtRazaoSocial.getText());
		emissora.setCnpj(txtCnpj.getText());
		emissora.setEndereco(enderecoForm.getEndereco());
		
		return emissora;
		
	}

	@Override
	protected void preencherFormulario(Emissora emissora) {
		
		txtNomeFantasia.setText(emissora.getNomeFantasia());
		txtRazaoSocial.setText(emissora.getRazaoSocial());
		txtCnpj.setText(emissora.getCnpj());
		enderecoForm.editar(emissora.getEndereco());
		
	}
	
	@Override
	protected String getScreenName() {
		return "Cadastro de Emissora";
	}
	
}
