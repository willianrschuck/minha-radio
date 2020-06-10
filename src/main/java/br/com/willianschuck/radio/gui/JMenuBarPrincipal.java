package br.com.willianschuck.radio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.willianschuck.radio.control.Controller;

public class JMenuBarPrincipal extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String CLIENTE_NOVO = "cliente_novo";
	private static final String CLIENTE_LISTA = "cliente_lista";
	private static final String ESTADO_NOVO = "estado_novo";
	private static final String ESTADO_LISTA = "estado_lista";

	private Controller controller;

	public JMenuBarPrincipal(Controller controller) {
		
		this.controller = controller;
		
		JMenu mCliente = new JMenu("Cliente");
		JMenuItem miClienteNovo = new JMenuItem("Novo");
		JMenuItem miClienteBuscar = new JMenuItem("Lista");
		
		miClienteNovo.setActionCommand(CLIENTE_NOVO);
		miClienteNovo.addActionListener(this);
		miClienteBuscar.setActionCommand(CLIENTE_LISTA);
		miClienteBuscar.addActionListener(this);
		
		mCliente.add(miClienteNovo);
		mCliente.add(miClienteBuscar);
		
		JMenu mEstado = new JMenu("Estado");
		JMenuItem miEstadoNovo = new JMenuItem("Novo");
		JMenuItem miEstadoLista = new JMenuItem("Lista");
		
		miEstadoNovo.setActionCommand(ESTADO_NOVO);
		miEstadoNovo.addActionListener(this);
		
		miEstadoLista.setActionCommand(ESTADO_LISTA);
		miEstadoLista.addActionListener(this);
		
		mEstado.add(miEstadoNovo);
		mEstado.add(miEstadoLista);
		
		JMenu mSobre = new JMenu("Sobre");
		
		this.add(mCliente);
		this.add(mEstado);
		this.add(mSobre);
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals(ESTADO_LISTA)) {
			controller.viewCard(Cards.ESTADO_LISTA);
		} else if (e.getActionCommand().equals(CLIENTE_LISTA)) {
			controller.viewCard(Cards.CLIENTE_LISTA);
		} else if (e.getActionCommand().equals(CLIENTE_NOVO)) {
			controller.viewCard(Cards.CLIENTE_FORM);
		}
		
	}
	
}
