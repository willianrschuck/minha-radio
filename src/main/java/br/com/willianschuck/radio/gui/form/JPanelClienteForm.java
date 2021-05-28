package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.cliente.ClienteValidator;
import br.com.willianschuck.radio.endereco.EnderecoService;
import br.com.willianschuck.radio.gui.JPanelBaseForm;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Cliente.TipoPessoa;
import br.com.willianschuck.util.Masks;

public class JPanelClienteForm extends JPanelBaseForm<Cliente> {
	private static final long serialVersionUID = 1L;

	private JPanel pnlForm;

	private JPanel infosPessoais;
	private JPanelEnderecoForm enderecoForm;

	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JLabel lblTipo;
	private JLabel lblCpf;
	private JLabel lblCnpj;
	private JLabel lblRamoAtividade;
	
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private ButtonGroup buttonGroup;
	private JRadioButton radFisica;
	private JRadioButton radJuridica;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtCnpj;
	private JTextField txtRamoAtividade;

	private JLabel lblRazaoSocial;

	private JTextField txtRazaoSocial;

	private EnderecoService enderecoService;
	
	public JPanelClienteForm(Controller controller, AbstractCrudService<Cliente> clienteService, EnderecoService enderecoService) {
		
		super(controller, "cliente_form", clienteService, new ClienteValidator());
		this.enderecoService = enderecoService;
		initComponents();
		
	}

	@Override
	public void editar(Cliente item) {
		super.editar(item);
	}

	@Override
	protected void initComponents() {
		
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());
		
		txtNome = new JTextField();
		txtRazaoSocial = new JTextField();
		txtTelefone = new JFormattedTextField(Masks.getMaskTelefone());
		txtEmail = new JTextField();
		txtCpf = new JFormattedTextField(Masks.getMaskCpf());
		txtCpf.setFocusLostBehavior(JFormattedTextField.COMMIT);
		txtCnpj = new JFormattedTextField(Masks.getMaskCnpj());
		txtRamoAtividade = new JTextField();
		
		buttonGroup = new ButtonGroup();
		radFisica = new JRadioButton("Física");
		radJuridica = new JRadioButton("Jurídica");
		
		ChangeListener radioChangeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				onChangeTipoPessoa();
			}
		};
		
		radFisica.addChangeListener(radioChangeListener);
		radJuridica.addChangeListener(radioChangeListener);
		
		buttonGroup.add(radFisica);
		buttonGroup.add(radJuridica);
		buttonGroup.setSelected(getRadioModel(TipoPessoa.FISICA), true);
		
		lblNome = new JLabel("Nome ", SwingConstants.RIGHT);
		lblRazaoSocial = new JLabel("Razão Social", SwingConstants.RIGHT);
		lblTelefone = new JLabel("Telefone ", SwingConstants.RIGHT);
		lblEmail = new JLabel("E-mail ", SwingConstants.RIGHT);
		lblTipo = new JLabel("Tipo ", SwingConstants.RIGHT);
		lblCpf = new JLabel("CPF ", SwingConstants.RIGHT);
		lblCnpj = new JLabel("CNPJ ", SwingConstants.RIGHT);
		lblRamoAtividade = new JLabel("Ramo de Atividade ", SwingConstants.RIGHT);
		
		infosPessoais = new JPanel(new GridBagLayout());
		
		int linha = 0;
		addToPanel(infosPessoais, lblNome, 0, linha, 3);
		addToPanel(infosPessoais, txtNome, 3, linha, 9, 1, 0);

		linha++;
		addToPanel(infosPessoais, lblRazaoSocial, 0, linha, 3);
		addToPanel(infosPessoais, txtRazaoSocial, 3, linha, 9, 1, 0);
		
		linha++;
		addToPanel(infosPessoais, lblTelefone, 0, linha, 3);
		addToPanel(infosPessoais, txtTelefone, 3, linha, 3, 1, 0);
		addToPanel(infosPessoais, lblEmail, 6, linha, 3);
		addToPanel(infosPessoais, txtEmail, 9, linha, 3, 1, 0);
		
		linha++;
		addToPanel(infosPessoais, lblTipo, 0, linha, 4);
		addToPanel(infosPessoais, radFisica, 4, linha, 4, 1, 0);
		addToPanel(infosPessoais, radJuridica, 8, linha, 4, 1, 0);
		
		linha++;
		addToPanel(infosPessoais, lblCpf, 0, linha, 3);
		addToPanel(infosPessoais, txtCpf, 3, linha, 9);

		linha++;
		addToPanel(infosPessoais, lblCnpj, 0, linha, 3);
		addToPanel(infosPessoais, txtCnpj, 3, linha, 9);

		linha++;
		addToPanel(infosPessoais, lblRamoAtividade, 0, linha, 3);
		addToPanel(infosPessoais, txtRamoAtividade, 3, linha, 9, 1, 0);

		enderecoForm = new JPanelEnderecoForm(enderecoService);		
		addToPanel(pnlForm, infosPessoais, 0, 0, 1, 1, 0);
		addToPanel(pnlForm, enderecoForm, 0, 1, 1, 1, 0);
		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);

		setContent(pnlForm);
		
	}
	
	private ButtonModel getRadioModel(TipoPessoa tipoPessoa) {
		if (tipoPessoa == TipoPessoa.FISICA) {
			return radFisica.getModel();
		}
		return radJuridica.getModel();
	}

	@Override
	protected Cliente montarObjeto(Cliente cliente) {
		
		cliente.setNomeFantasia(txtNome.getText());
		cliente.setTelefone(txtTelefone.getText().replaceAll("\\D", ""));
		cliente.setEmail(txtEmail.getText());
		cliente.setTipoPessoa(getTipoPessoa());
		cliente.setRamoAtividade(txtRamoAtividade.getText());
		
		if (isPessoaFisica()) {
		
			cliente.setRazaoSocial("");
			cliente.setCpf(txtCpf.getText().replaceAll("\\D", ""));
			cliente.setCnpj(null);
			
		} else {

			cliente.setRazaoSocial(txtRazaoSocial.getText());
			cliente.setCnpj(txtCnpj.getText());
			cliente.setCpf(null);
			
		}
		
		cliente.setEndereco(enderecoForm.getEndereco());
		
		if (cliente.getDataCadastro() == null) {
			cliente.setDataCadastro(new Date());
		}
		
		return cliente;
		
	}

	@Override
	protected void preencherFormulario(Cliente cliente) {
		
		txtNome.setText(cliente.getNomeFantasia());
		txtRazaoSocial.setText(cliente.getRazaoSocial());
		txtTelefone.setText(cliente.getTelefone());
		txtEmail.setText(cliente.getEmail());
		txtCpf.setValue(cliente.getCpf());
		txtCnpj.setValue(cliente.getCnpj());
		txtRamoAtividade.setText(cliente.getRamoAtividade());
		buttonGroup.setSelected(getRadioModel(cliente.getTipoPessoa()), true);
		toggleElements(cliente.getId() == null);
		enderecoForm.editar(cliente.getEndereco());
		
	}

	private void toggleElements(boolean enabled) {
		toggleRadio(radFisica, enabled);
		toggleRadio(radJuridica, enabled);
	}

	private void toggleRadio(JRadioButton btn, boolean enabled) {
		btn.setEnabled(enabled || btn.isSelected());
	}

	private TipoPessoa getTipoPessoa() {
		if (isPessoaFisica()) {
			return TipoPessoa.FISICA;
		}
		return TipoPessoa.JURIDICA;
	}
	
	private boolean isPessoaFisica() {
		return radFisica.getModel() == buttonGroup.getSelection();
	}
	
	private void onChangeTipoPessoa() {
		
		boolean isPessoaFisica = isPessoaFisica();
		
		txtCpf.setEnabled(isPessoaFisica);
		txtCnpj.setEnabled(!isPessoaFisica);
		txtRazaoSocial.setEnabled(!isPessoaFisica);
		
	}

	@Override
	protected String getScreenName() {
		return "Cadastro de Cliente";
	}
	
}
