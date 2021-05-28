package br.com.willianschuck.radio.gui.form;

import br.com.willianschuck.radio.endereco.EnderecoService;
import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.model.Estado;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

import static br.com.willianschuck.util.PanelUtil.addToPanel;

public class JPanelEnderecoForm extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Endereco endereco;

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

	private final EnderecoService enderecoService;
	
	public JPanelEnderecoForm(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
		initComponents();
	}
	
	private void initComponents() {
		
		setLayout(new GridBagLayout());
		setBorder(new TitledBorder("Endereço"));
		
		lblLogradouro = new JLabel("Logradouro ", SwingConstants.RIGHT);
		lblNumero = new JLabel("Numero ", SwingConstants.RIGHT);
		lblBairro = new JLabel("Bairro ", SwingConstants.RIGHT);
		lblCidade = new JLabel("Cidade ", SwingConstants.RIGHT);
		lblEstado = new JLabel("Estado ", SwingConstants.RIGHT);
		
		txtLogradouro = new JTextField();
		txtNumero = new JTextField();
		txtBairro = new JTextField();
		cbxEstado = new JComboBox<>();
		cbxCidade = new JComboBox<>();
		
		cbxEstado.addActionListener(e -> popularCbxCidade());
		cbxCidade.setEnabled(false);
		
		popularCbxEstado();
		
		addToPanel(this, lblEstado, 0, 0, 1);
		addToPanel(this, cbxEstado, 1, 0, 1, 1, 0);
		
		addToPanel(this, lblCidade, 0, 1, 1);
		addToPanel(this, cbxCidade, 1, 1, 1, 1, 0);
		
		addToPanel(this, lblLogradouro, 0, 2, 1);
		addToPanel(this, txtLogradouro, 1, 2, 3, 1, 0);
		
		addToPanel(this, lblBairro, 0, 3, 1);
		addToPanel(this, txtBairro, 1, 3, 1, 1, 0);
		
		addToPanel(this, lblNumero, 0, 4, 1);
		addToPanel(this, txtNumero, 1, 4, 1, 1, 0);
		
	}
	
	public void editar(Endereco endereco) {
		this.endereco = endereco;
		preencherCampos();
	}
	
	private void preencherCampos() {
		
		txtLogradouro.setText(endereco.getLogradouro());
		txtBairro.setText(endereco.getBairro());
		txtNumero.setText(endereco.getNumero());

		if (endereco.getCidade() != null) {
			cbxEstado.setSelectedItem(endereco.getCidade().getEstado());
			cbxCidade.setSelectedItem(endereco.getCidade());
		} else {
			cbxEstado.setSelectedIndex(0);
		}
		
	}

	public Endereco getEndereco() {
		
		preencherObjeto();
		return endereco;
		
	}
	
	private void preencherObjeto() {
		
		endereco.setLogradouro(txtLogradouro.getText());
		endereco.setBairro(txtBairro.getText());
		endereco.setNumero(txtNumero.getText());
		endereco.setCidade((Cidade) cbxCidade.getSelectedItem());
		
	}

	private void popularCbxEstado() {
		
		Estado padrao = new Estado();
		padrao.setNome("Selecione...");
		
		List<Estado> estados = enderecoService.getEstados();
		
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
			
			List<Cidade> cidades = enderecoService.getCidadesFrom(estado);
			for (Cidade cidade : cidades) {
				cbxCidade.addItem(cidade);
			}
			cbxCidade.setEnabled(true);
			
		} else {
			
			cbxCidade.setEnabled(false);
			
		}
		
	}
	
}
