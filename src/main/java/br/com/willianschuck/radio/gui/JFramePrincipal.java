package br.com.willianschuck.radio.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFramePrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private CardLayout cardLayout;
	private JPanel pnlCard;
	
	public JFramePrincipal() {
		
		setLayout(new BorderLayout());
		
		cardLayout = new CardLayout();
		pnlCard = new JPanel();
		pnlCard.setLayout(cardLayout);
		
		this.add(pnlCard, BorderLayout.CENTER);
		
		this.setTitle("Minha Rádio");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public void addCard(JPanel panel, String name) {
		pnlCard.add(panel, name);
	}
	
	public void addCard(JPanelBase panel) {
		pnlCard.add(panel, panel.getCardName());
	}
	
	public void viewCard(String name) {
		cardLayout.show(pnlCard, name);
	}
	
}
