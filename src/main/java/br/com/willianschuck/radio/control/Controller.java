package br.com.willianschuck.radio.control;

import javax.swing.JFrame;

import br.com.willianschuck.radio.dao.CidadeDAO;
import br.com.willianschuck.radio.dao.EstadoDAO;
import br.com.willianschuck.radio.gui.JFramePrincipal;
import br.com.willianschuck.radio.gui.JMenuBarPrincipal;
import br.com.willianschuck.radio.gui.form.JPanelClienteForm;
import br.com.willianschuck.radio.gui.form.JPanelContratoForm;
import br.com.willianschuck.radio.gui.form.JPanelEmissoraForm;
import br.com.willianschuck.radio.gui.lista.JPanelClienteLista;
import br.com.willianschuck.radio.gui.lista.JPanelContratoLista;
import br.com.willianschuck.radio.gui.lista.JPanelEmissoraLista;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.service.ClienteService;
import br.com.willianschuck.radio.service.ContratoService;
import br.com.willianschuck.radio.service.EmissoraService;

public class Controller {

	private JFramePrincipal mainFrame;
	
	private final ClienteService clienteService = new ClienteService();
	private final EmissoraService emissoraService = new EmissoraService();
	private final ContratoService contratoService = new ContratoService();
	
	private final CidadeDAO cidadeDao = new CidadeDAO();
	private final EstadoDAO estadoDao = new EstadoDAO();

	private JPanelClienteForm pClienteForm;
	private JPanelClienteLista pClienteLista;
	private JPanelEmissoraForm pEmissoraForm;
	private JPanelEmissoraLista pEmissoraList;
	private JPanelContratoForm pContratoForm;

	private JPanelContratoLista pContratoList;

	
	public Controller() {
		initComponents();
	}
	
	private void initComponents() {
		
		mainFrame = new JFramePrincipal();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		
		
		// --- Painéis Cliente
		
		pClienteForm = new JPanelClienteForm(this);
		pClienteLista = new JPanelClienteLista(this);
		
		Actions<Cliente> clienteActions = new Actions<Cliente>() {

			public void editar(Cliente c) {
				pClienteForm.editar(c);
				viewCard(pClienteForm.getCardName());
			}
			
			public void listar() {
				pClienteLista.updateListData();
				viewCard(pClienteLista.getCardName());
			}
			
		};
		
		pClienteForm.setActions(clienteActions);
		pClienteLista.setActions(clienteActions);
		
		mainFrame.addCard(pClienteForm, pClienteForm.getCardName());
		mainFrame.addCard(pClienteLista, pClienteLista.getCardName());
	
		
		// --- Painéis Emissora
		
		pEmissoraForm = new JPanelEmissoraForm(this);
		pEmissoraList = new JPanelEmissoraLista(this);
		Actions<Emissora> emissoraActions = new Actions<Emissora>() {

			public void editar(Emissora e) {
				pEmissoraForm.editar(e);
				viewCard(pEmissoraForm.getCardName());
			}

			public void listar() {
				pEmissoraList.updateListData();
				viewCard(pEmissoraList.getCardName());
			}
			
		};
		pEmissoraForm.setEmissoraActions(emissoraActions);
		pEmissoraList.setActions(emissoraActions);
		
		mainFrame.addCard(pEmissoraForm, pEmissoraForm.getCardName());
		mainFrame.addCard(pEmissoraList);
		
		
		// ----- Painéis Contrato
		
		pContratoForm = new JPanelContratoForm(this);
		pContratoList = new JPanelContratoLista(this);
		Actions<Contrato> contratoActions = new Actions<Contrato>() {
			
			public void editar(Contrato c) {
				pContratoForm.editar(c);
			}
			
			public void listar() {
				pContratoList.updateListData();
				viewCard(pContratoList.getCardName());
			}
			
		};
		pContratoForm.setActions(contratoActions);
		pContratoList.setActions(contratoActions);
		mainFrame.addCard(pContratoForm);
		mainFrame.addCard(pContratoList);
		
		JMenuBarPrincipal menuBarPrincipal = new JMenuBarPrincipal(this);
		menuBarPrincipal.setClienteActions(clienteActions);
		menuBarPrincipal.setEmissoraActions(emissoraActions);
		menuBarPrincipal.setContratoActions(contratoActions);
		
		mainFrame.setJMenuBar(menuBarPrincipal);
		
		mainFrame.setSize(640, 480);
		mainFrame.setResizable(false);
		mainFrame.setExtendedState(JFrame.NORMAL); 
		mainFrame.setVisible(true);
		
	}
	
	public void viewCard(String card) {
		mainFrame.viewCard(card);
	}
	
	public ClienteService getClienteService() {
		return clienteService;
	}

	public EmissoraService getEmissoraService() {
		return emissoraService;
	}

	public ContratoService getContratoService() {
		return contratoService ;
	}
	
	public CidadeDAO getCidadeDao() {
		return cidadeDao;
	}
	
	public EstadoDAO getEstadoDao() {
		return estadoDao;
	}

}
