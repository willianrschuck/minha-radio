package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.DateUtil.formatDate;
import static br.com.willianschuck.util.NumberUtil.asDouble;
import static br.com.willianschuck.util.NumberUtil.asInteger;
import static br.com.willianschuck.util.DateUtil.asDate;
import static br.com.willianschuck.util.ObjectUtil.getAsString;
import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseForm;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.service.ContratoService;

public class JPanelContratoForm extends JPanelBaseForm<Contrato> {
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlForm;

	private JLabel lblEmissora;
	private JLabel lblCliente;
	private JLabel lblPrazo;
	private JLabel lblDataInicio;
	private JLabel lblDataTermino;
	private JLabel lblPrecoMensal;
	private JLabel lblDiscriminacaoInsercoes;

	private JComboBox<Emissora> cbxEmissora;
	private JComboBox<Cliente> cbxCliente;
	private JTextField txtPrazo;
	private JTextArea txtDiscriminacaoInsercoes;
	private JTextField txtDataInicio;
	private JTextField txtDataTermino;
	private JTextField txtPrecoMensal;

	public JPanelContratoForm(Controller controller) {
		
		super(controller, "contrato_form", new ContratoService());
		initComponents();
		
	}

	@Override
	protected void initComponents() {
		
		super.initComponents();
		
		pnlForm = new JPanel(new GridBagLayout());

		lblEmissora = new JLabel("Emissora");
		lblCliente = new JLabel("Cliente");
		lblPrazo = new JLabel("Prazo");
		lblDataInicio = new JLabel("Data de Início");
		lblDataTermino = new JLabel("Data de Término");
		lblPrecoMensal = new JLabel("Preço Mensal");
		lblDiscriminacaoInsercoes = new JLabel("Discriminacao das Inserções");
		
		cbxEmissora = new JComboBox<Emissora>();
		cbxCliente = new JComboBox<Cliente>();
		txtPrazo = new JTextField();
		txtDataInicio = new JTextField();
		txtDataTermino = new JTextField();
		txtPrecoMensal = new JTextField();
		txtDiscriminacaoInsercoes = new JTextArea();
		
		addToPanel(pnlForm, lblEmissora, 0, 0, 1);
		addToPanel(pnlForm, cbxEmissora, 1, 0, 1, 1, 0);
		
		addToPanel(pnlForm, lblCliente, 0, 1, 1);
		addToPanel(pnlForm, cbxCliente, 1, 1, 1, 1, 0);

		addToPanel(pnlForm, lblPrazo, 0, 2, 1);
		addToPanel(pnlForm, txtPrazo, 1, 2, 1, 1, 0);
		
		addToPanel(pnlForm, lblDataInicio, 0, 3, 1);
		addToPanel(pnlForm, txtDataInicio, 1, 3, 1, 1, 0);
		
		addToPanel(pnlForm, lblDataTermino, 0, 4, 1);
		addToPanel(pnlForm, txtDataTermino, 1, 4, 1, 1, 0);
		
		addToPanel(pnlForm, lblPrecoMensal, 0, 5, 1);
		addToPanel(pnlForm, txtPrecoMensal, 1, 5, 1, 1, 0);
		
		addToPanel(pnlForm, lblDiscriminacaoInsercoes, 0, 6, 1);
		addToPanel(pnlForm, txtDiscriminacaoInsercoes, 1, 6, 1, 1, 0);

		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);
		
		popularCbxCliente();
		popularCbxEmissora();
		
		setContent(pnlForm);
		
	}
	
	private void popularCbxCliente() {
		
		Cliente optPadrao = new Cliente();
		optPadrao.setNome("Selecione...");
		cbxCliente.addItem(optPadrao);
		
		List<Cliente> clientes = controller().getClienteService().getAll();
		for (Cliente cliente : clientes) {
			cbxCliente.addItem(cliente);
		}
		cbxCliente.setSelectedItem(optPadrao);
		
	}

	private void popularCbxEmissora() {

		Emissora optPadrao = new Emissora();
		optPadrao.setNomeFantasia("Selecione...");
		cbxEmissora.addItem(optPadrao);
		
		List<Emissora> emissoras = controller().getEmissoraService().getAll();
		for (Emissora emissora : emissoras) {
			cbxEmissora.addItem(emissora);
		}
		cbxEmissora.setSelectedItem(optPadrao);
		
	}

	@Override
	protected Contrato montarObjeto(Contrato contrato) {
		
		if (contrato == null) {
			contrato = controller().getContratoService().create();
		}
		
		contrato.setCliente((Cliente) cbxCliente.getSelectedItem());
		contrato.setEmissora((Emissora) cbxEmissora.getSelectedItem());
		contrato.setDataFinal(asDate(txtDataInicio.getText()));
		contrato.setDataFinal(asDate(txtDataTermino.getText()));
		contrato.setPrazo(asInteger(txtPrazo.getText()));
		contrato.setPrecoMensal(asDouble(txtPrecoMensal.getText()));
		contrato.setDiscriminacaoInsercoes(txtDiscriminacaoInsercoes.getText());
		
		return contrato;
		
	}

	@Override
	protected void preencherFormulario(Contrato contrato) {
		
		if (contrato == null) {
			contrato = controller().getContratoService().create();
		}

		
		if (contrato.getCliente() != null) {
			cbxCliente.setSelectedItem(contrato.getCliente());
		} else {
			cbxCliente.setSelectedIndex(0);
		}
		
		if (contrato.getEmissora() != null) {
			cbxEmissora.setSelectedItem(contrato.getEmissora());
		} else {
			cbxEmissora.setSelectedIndex(0);
		}
		
		txtDataInicio.setText(formatDate(contrato.getDataInicio()));
		txtDataTermino.setText(formatDate(contrato.getDataFinal()));
		txtPrazo.setText(getAsString(contrato.getPrazo()));
		txtPrecoMensal.setText(getAsString(contrato.getPrecoMensal()));
		txtDiscriminacaoInsercoes.setText(contrato.getDiscriminacaoInsercoes());
		
		
	}

}
