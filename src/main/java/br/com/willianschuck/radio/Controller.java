package br.com.willianschuck.radio;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.com.willianschuck.base.ReportService;
import br.com.willianschuck.radio.cliente.ClienteServiceImpl;
import br.com.willianschuck.radio.conta.ContaService;
import br.com.willianschuck.radio.conta.ContaServiceImpl;
import br.com.willianschuck.radio.contrato.ContratoReportServiceImpl;
import br.com.willianschuck.radio.contrato.ContratoServiceImpl;
import br.com.willianschuck.radio.emissora.EmissoraServiceImpl;
import br.com.willianschuck.radio.endereco.EnderecoService;
import br.com.willianschuck.radio.endereco.EnderecoServiceImpl;
import br.com.willianschuck.radio.gui.Actions;
import br.com.willianschuck.radio.gui.ComponentFactory;
import br.com.willianschuck.radio.gui.JFramePrincipal;
import br.com.willianschuck.radio.gui.JPanelMenuLateral;
import br.com.willianschuck.radio.gui.form.JPanelClienteForm;
import br.com.willianschuck.radio.gui.form.JPanelContratoForm;
import br.com.willianschuck.radio.gui.lista.JPanelClienteLista;
import br.com.willianschuck.radio.gui.lista.JPanelContaLista;
import br.com.willianschuck.radio.gui.lista.JPanelContratoLista;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Conta;
import br.com.willianschuck.radio.model.Contrato;

public class Controller {

	private JFramePrincipal mainFrame;
	
	private final ClienteServiceImpl clienteService = new ClienteServiceImpl();
	private final EmissoraServiceImpl emissoraService = new EmissoraServiceImpl();
	private final ContratoServiceImpl contratoService = new ContratoServiceImpl();
	private final EnderecoService enderecoService = new EnderecoServiceImpl();
	private final ReportService<Contrato> contratoReportService = new ContratoReportServiceImpl();
	

	private JPanelClienteForm pClienteForm;
	private JPanelClienteLista pClienteLista;
//	private JPanelEmissoraForm pEmissoraForm;
//	private JPanelEmissoraLista pEmissoraList;
	private JPanelContratoForm pContratoForm;

	private JPanelContratoLista pContratoList;

	private ContaService contaService = new ContaServiceImpl();

	private JPanelContaLista pContasList;

	
	public Controller() {
		initComponents();
	}
	
	private void initComponents() {
		
		mainFrame = new JFramePrincipal();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		
		
		// --- Painéis Cliente
		
		pClienteForm = new JPanelClienteForm(this, clienteService, enderecoService);
		pClienteLista = new JPanelClienteLista(this, clienteService);
		
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
//		
//		pEmissoraForm = new JPanelEmissoraForm(this, emissoraService, enderecoService);
//		pEmissoraList = new JPanelEmissoraLista(this, emissoraService);
//		Actions<Emissora> emissoraActions = new Actions<Emissora>() {
//
//			public void editar(Emissora e) {
//				pEmissoraForm.editar(e);
//				viewCard(pEmissoraForm.getCardName());
//			}
//
//			public void listar() {
//				pEmissoraList.updateListData();
//				viewCard(pEmissoraList.getCardName());
//			}
//			
//		};
//		pEmissoraForm.setActions(emissoraActions);
//		pEmissoraList.setActions(emissoraActions);
		
//		mainFrame.addCard(pEmissoraForm, pEmissoraForm.getCardName());
//		mainFrame.addCard(pEmissoraList);
		
		
		// ----- Painéis Contrato
		
		pContratoForm = new JPanelContratoForm(this, contratoService, clienteService, emissoraService);
		pContratoList = new JPanelContratoLista(this, contratoService, contratoReportService);
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
		
		
		JPanelMenuLateral sidebar = new JPanelMenuLateral(this);
		JButton clientes = ComponentFactory.makeSidebarButton("Cliente");
		clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clienteActions.listar();
			}
		});
		JButton anuncios = ComponentFactory.makeSidebarButton("Anúncios");
		anuncios.setEnabled(false);
		JButton contratos = ComponentFactory.makeSidebarButton("Contratos");
		contratos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				contratoActions.listar();
			}
		});
		
		
		pContasList = new JPanelContaLista(this, contaService );
		Actions<Conta> contasAction = new Actions<Conta>() {
			
			public void editar(Conta c) {
			}
			
			public void listar() {
				pContasList.updateListData();
				viewCard(pContasList.getCardName());
			}
			
		};
		pContasList.setActions(contasAction);
		mainFrame.addCard(pContasList);
		
		JButton contas = ComponentFactory.makeSidebarButton("Contabilidade");
		contas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				contasAction.listar();			
			}
		});
//		contas.setEnabled(false);
		sidebar.addButton(clientes);
		sidebar.addButton(anuncios);
		sidebar.addButton(contratos);
		sidebar.addButton(contas);
		mainFrame.add(sidebar, BorderLayout.WEST);
		
		mainFrame.setExtendedState(JFrame.NORMAL); 
		mainFrame.setVisible(true);
		
	}
	
	public void viewCard(String card) {
		mainFrame.viewCard(card);
	}

}
