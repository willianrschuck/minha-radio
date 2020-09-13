package br.com.willianschuck.radio.gui.form;

import static br.com.willianschuck.util.DateUtil.asDate;
import static br.com.willianschuck.util.DateUtil.formatDate;
import static br.com.willianschuck.util.NumberUtil.asInteger;
import static br.com.willianschuck.util.NumberUtil.formatCurrency;
import static br.com.willianschuck.util.NumberUtil.fromCurrency;
import static br.com.willianschuck.util.ObjectUtil.getAsString;
import static br.com.willianschuck.util.PanelUtil.addToPanel;

import java.awt.GridBagLayout;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.MaskFormatter;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseForm;
import br.com.willianschuck.radio.gui.listeners.ChangedValueListener;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.util.NumberUtil;

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
	private JLabel lblValorTotal;

	private JComboBox<Emissora> cbxEmissora;
	private JComboBox<Cliente> cbxCliente;
	private JTextField txtPrazo;
	private JTextArea txtDiscriminacaoInsercoes;
	private JFormattedTextField txtDataInicio;
	private JFormattedTextField txtDataTermino;
	private JFormattedTextField txtPrecoMensal;
	private JFormattedTextField txtValorTotal;

	private MaskFormatter dateFormatter;

	private AbstractCrudService<Cliente> clienteService;
	private AbstractCrudService<Emissora> emissoraService;
	
	public JPanelContratoForm(Controller controller, AbstractCrudService<Contrato> contratoService, AbstractCrudService<Cliente> clienteService, AbstractCrudService<Emissora> emissoraService) {
		
		super(controller, "contrato_form", contratoService);
		this.clienteService = clienteService;
		this.emissoraService = emissoraService;
		initComponents();
		
	}

	@Override
	protected void initComponents() {
		
		super.initComponents();
		
		try {
			dateFormatter = new MaskFormatter("##/##/####");
			dateFormatter.setPlaceholderCharacter('_');
			dateFormatter.setValueContainsLiteralCharacters(true);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		pnlForm = new JPanel(new GridBagLayout());

		lblEmissora = new JLabel("Emissora");
		lblCliente = new JLabel("Cliente");
		lblPrazo = new JLabel("Prazo");
		lblDataInicio = new JLabel("Data de Início");
		lblDataTermino = new JLabel("Data de Término");
		lblPrecoMensal = new JLabel("Preço Mensal");
		lblDiscriminacaoInsercoes = new JLabel("Discriminacao das Inserções");
		lblValorTotal = new JLabel("Valor Total");
		
		cbxEmissora = new JComboBox<Emissora>();
		cbxCliente = new JComboBox<Cliente>();
		txtPrazo = new JTextField();
		txtDataInicio = new JFormattedTextField(dateFormatter);
		txtDataTermino = new JFormattedTextField(dateFormatter);
		txtDataTermino.setEditable(false);
		txtPrecoMensal = new JFormattedTextField(NumberUtil.CURRENCY);
		txtValorTotal = new JFormattedTextField(NumberUtil.CURRENCY);
		txtValorTotal.setEditable(false);
		txtDiscriminacaoInsercoes = new JTextArea();
		
		ChangedValueListener updateTotalListener = new ChangedValueListener();
		updateTotalListener.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcularValorTotal();
			}
		});
		
		txtPrazo.getDocument().addDocumentListener(updateTotalListener);
		txtPrecoMensal.getDocument().addDocumentListener(updateTotalListener);

		ChangedValueListener updateDataFinalListener = new ChangedValueListener();
		updateDataFinalListener.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				calcularDataFinal();
			}
		});
		txtDataInicio.getDocument().addDocumentListener(updateDataFinalListener);
		txtPrazo.getDocument().addDocumentListener(updateDataFinalListener);

		addToPanel(pnlForm, lblEmissora, 0, 0,1);
		addToPanel(pnlForm, cbxEmissora, 1, 0, 3, 1, 0);
		
		addToPanel(pnlForm, lblCliente, 0, 1, 1);
		addToPanel(pnlForm, cbxCliente, 1, 1, 3, 1, 0);

		addToPanel(pnlForm, lblPrazo, 0, 2, 1);
		addToPanel(pnlForm, txtPrazo, 1, 2, 3, 1, 0);
		
		addToPanel(pnlForm, lblDataInicio, 0, 3, 1);
		addToPanel(pnlForm, txtDataInicio, 1, 3, 1, 1, 0);
		addToPanel(pnlForm, lblDataTermino, 2, 3, 1);
		addToPanel(pnlForm, txtDataTermino, 3, 3, 1, 1, 0);
		
		addToPanel(pnlForm, lblPrecoMensal, 0, 4, 1);
		addToPanel(pnlForm, txtPrecoMensal, 1, 4, 3, 1, 0);
		
		addToPanel(pnlForm, lblValorTotal, 0, 5, 1);
		addToPanel(pnlForm, txtValorTotal, 1, 5, 3, 1, 0);
		
		addToPanel(pnlForm, lblDiscriminacaoInsercoes, 0, 6, 1);
		addToPanel(pnlForm, txtDiscriminacaoInsercoes, 1, 6, 3, 1, 0);

		addToPanel(pnlForm, new JLabel(), 0, 99, 12, 0, 1);
		
		popularCbxCliente();
		popularCbxEmissora();
		
		setContent(pnlForm);
		
	}
	
	private void popularCbxCliente() {
		
		Cliente optPadrao = new Cliente();
		optPadrao.setNomeFantasia("Selecione...");
		cbxCliente.addItem(optPadrao);
		
		List<Cliente> clientes = clienteService.getAll();
		for (Cliente cliente : clientes) {
			cbxCliente.addItem(cliente);
		}
		cbxCliente.setSelectedItem(optPadrao);
		
	}

	private void popularCbxEmissora() {

		Emissora optPadrao = new Emissora();
		optPadrao.setNomeFantasia("Selecione...");
		cbxEmissora.addItem(optPadrao);
		
		List<Emissora> emissoras = emissoraService.getAll();
		for (Emissora emissora : emissoras) {
			cbxEmissora.addItem(emissora);
		}
		cbxEmissora.setSelectedItem(optPadrao);
		
	}
	
	private void calcularValorTotal() {
		
		Double precoMensal = fromCurrency(txtPrecoMensal.getText());
		Integer prazo = asInteger(txtPrazo.getText());
		
		String valorTotal = "";
		if (precoMensal != null && prazo != null) {
			valorTotal = NumberUtil.CURRENCY.format(precoMensal * prazo);
		}
		txtValorTotal.setText(valorTotal);
		
	}
	
	private void calcularDataFinal() {
		
		Date date = asDate(txtDataInicio.getText());
		Integer prazo = asInteger(txtPrazo.getText());
		
		if (date != null && prazo != null && prazo > 1) {
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate plusMonths = localDate.plusMonths(prazo);
			txtDataTermino.setText(formatDate(Date.from(plusMonths.atStartOfDay(ZoneId.systemDefault()).toInstant())));
		}
		
	}

	@Override
	protected Contrato montarObjeto(Contrato contrato) {
		
		contrato.setCliente((Cliente) cbxCliente.getSelectedItem());
		contrato.setEmissora((Emissora) cbxEmissora.getSelectedItem());
		contrato.setDataFinal(asDate(txtDataInicio.getText()));
		contrato.setDataFinal(asDate(txtDataTermino.getText()));
		contrato.setPrazo(asInteger(txtPrazo.getText()));
		contrato.setValorTotal(fromCurrency(txtValorTotal.getText()));
		contrato.setPrecoMensal(fromCurrency(txtPrecoMensal.getText()));
		contrato.setDiscriminacaoInsercoes(txtDiscriminacaoInsercoes.getText());
		
		return contrato;
		
	}

	@Override
	protected void preencherFormulario(Contrato contrato) {
		
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
		txtPrecoMensal.setText(formatCurrency(contrato.getPrecoMensal()));
		txtValorTotal.setText(formatCurrency(contrato.getValorTotal()));
		txtDiscriminacaoInsercoes.setText(contrato.getDiscriminacaoInsercoes());
		
		
	}
	
	@Override
	protected String getScreenName() {
		return "Cadastro de Contrato";
	}

}
