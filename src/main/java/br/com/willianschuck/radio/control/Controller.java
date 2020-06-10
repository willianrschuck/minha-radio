package br.com.willianschuck.radio.control;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.willianschuck.radio.dao.CidadeDAO;
import br.com.willianschuck.radio.dao.EstadoDAO;
import br.com.willianschuck.radio.gui.Cards;
import br.com.willianschuck.radio.gui.JFramePrincipal;
import br.com.willianschuck.radio.gui.JMenuBarPrincipal;
import br.com.willianschuck.radio.gui.cliente.JPanelClienteForm;
import br.com.willianschuck.radio.gui.cliente.JPanelClienteLista;
import br.com.willianschuck.radio.gui.estado.JPanelListagemEstado;
import br.com.willianschuck.radio.service.ClienteService;

public class Controller {

	private JFramePrincipal mainFrame;
	
	private JPanel pListagemEstado;
	private JPanelClienteForm pClienteForm;
	
	private final EstadoDAO estadoDao = new EstadoDAO();
	private final CidadeDAO cidadeDao = new CidadeDAO();
	private final ClienteService clienteService = new ClienteService();

	private JPanelClienteLista pClienteLista;
	
	public Controller() {
		initComponents();
	}
	
	private void initComponents() {
		mainFrame = new JFramePrincipal();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		
		pListagemEstado = new JPanelListagemEstado(this);
		pClienteForm = new JPanelClienteForm(this);
		pClienteLista = new JPanelClienteLista(this, pClienteForm);
		
		mainFrame.addCard(pListagemEstado, Cards.ESTADO_LISTA);
		mainFrame.addCard(pClienteForm, Cards.CLIENTE_FORM);
		mainFrame.addCard(pClienteLista, Cards.CLIENTE_LISTA);
		
		mainFrame.setJMenuBar(new JMenuBarPrincipal(this));
		
		mainFrame.setSize(640, 480);
		mainFrame.setResizable(false);
		mainFrame.setExtendedState(JFrame.NORMAL); 
		mainFrame.setVisible(true);
		
	}
	
	public void viewCard(String card) {
		mainFrame.viewCard(card);
	}
	
	public EstadoDAO getEstadoDao() {
		return estadoDao;
	}
	
	public CidadeDAO getCidadeDao() {
		return cidadeDao;
	}
	
	public ClienteService getClienteService() {
		return clienteService;
	}

}
