package br.com.willianschuck.radio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.willianschuck.radio.control.Actions;
import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;

public class JMenuBarPrincipal extends JMenuBar implements ActionListener {


	private static final long serialVersionUID = 1L;

	private static final String CLIENTE_CADASTRO = "cliente_cadastro";
	private static final String CLIENTE_LISTA = "cliente_lista";

	private static final String EMISSORA_CADASTRO = "emissora_cadastro";
	private static final String EMISSORA_LISTA = "emissora_lista";

	private static final String CONTRATO_CADASTRO = "contrato_cadastro";
	private static final String CONTRATO_LISTA = "contrato_lista";
	
	private Controller controller;

	private Actions<Cliente> clienteActions;
	private Actions<Emissora> emissoraActions;

	private Actions<Contrato> contratoActions;

	public JMenuBarPrincipal(Controller controller) {
		
		this.controller = controller;
		
		JMenu mCliente = getMenuCliente();
		JMenu mEmissora = getMenuEmissora();
		JMenu mContrato = getMenuContrato();
		
		add(mCliente);
		add(mEmissora);
		add(mContrato);
		
	}
	
	private JMenu getMenuCliente() {
		
		JMenu mCliente = new JMenu("Cliente");
		JMenuItem miClienteNovo = new JMenuItem("Cadastrar");
		JMenuItem miClienteBuscar = new JMenuItem("Ver registros");
		
		miClienteNovo.setActionCommand(CLIENTE_CADASTRO);
		miClienteNovo.addActionListener(this);
		miClienteBuscar.setActionCommand(CLIENTE_LISTA);
		miClienteBuscar.addActionListener(this);
		
		mCliente.add(miClienteNovo);
		mCliente.add(miClienteBuscar);
		
		return mCliente;
		
	}
	
	private JMenu getMenuEmissora() {
		
		JMenu mEmissora = new JMenu("Emissora");
		JMenuItem miEmissoraCadastrar = new JMenuItem("Cadastrar");
		JMenuItem miEmissoraListar = new JMenuItem("Listar");
		
		miEmissoraCadastrar.setActionCommand(EMISSORA_CADASTRO);
		miEmissoraCadastrar.addActionListener(this);
		
		miEmissoraListar.setActionCommand(EMISSORA_LISTA);
		miEmissoraListar.addActionListener(this);
			
		mEmissora.add(miEmissoraCadastrar);
		mEmissora.add(miEmissoraListar);
		
		return mEmissora;
		
	}
	
	private JMenu getMenuContrato() {
		
		JMenu mEmissora = new JMenu("Contrato");
		
		JMenuItem miEmissoraCadastrar = new JMenuItem("Cadastrar");
		miEmissoraCadastrar.setActionCommand(CONTRATO_CADASTRO);
		miEmissoraCadastrar.addActionListener(this);
		mEmissora.add(miEmissoraCadastrar);

		JMenuItem miEmissoraListar = new JMenuItem("Listar");
		miEmissoraListar.setActionCommand(CONTRATO_LISTA);
		miEmissoraListar.addActionListener(this);
		mEmissora.add(miEmissoraListar);
		
		return mEmissora;
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(CLIENTE_LISTA)) {
			clienteActions.listar();
		} else if (e.getActionCommand().equals(CLIENTE_CADASTRO)) {
			clienteActions.editar(controller.getClienteService().create());
		} else if (e.getActionCommand().equals(EMISSORA_CADASTRO)) {
			emissoraActions.editar(controller.getEmissoraService().create());
		} else if (e.getActionCommand().equals(EMISSORA_LISTA)) {
			emissoraActions.listar();
		} else if (e.getActionCommand().equals(CONTRATO_CADASTRO)) {
			contratoActions.editar(null);
		} else if (e.getActionCommand().equals(CONTRATO_LISTA)) {
			contratoActions.listar();
		}
		
	}
	
	public void setClienteActions(Actions<Cliente> clienteActions) {
		this.clienteActions = clienteActions;
	}
	
	public void setEmissoraActions(Actions<Emissora> emissoraActions) {
		this.emissoraActions = emissoraActions;
	}

	public void setContratoActions(Actions<Contrato> contratoActions) {
		this.contratoActions = contratoActions;
	}
	
}
