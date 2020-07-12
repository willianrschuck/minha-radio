package br.com.willianschuck.radio.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import br.com.willianschuck.radio.control.Controller;

public abstract class JPanelBase extends JPanel {
	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private JToolBar toolBar;
	private JComponent content;
	
	private String cardName;
	
	public JPanelBase(Controller controller, String cardName) {
		this.controller = controller;
		this.cardName = cardName;
	}

	protected void initComponents() {
		
		setLayout(new BorderLayout());
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		add(toolBar, BorderLayout.NORTH);
		
	}

	public final void addToolbarItem(JButton btn) {
		toolBar.add(btn);
	}
	
	public final void setContent(JComponent contentPane) {
		
		if (this.content == null) {
			updateContent(contentPane);
		} else {
			remove(this.content);
			updateContent(contentPane);
		}
		
	}

	private void updateContent(JComponent content) {
		this.content = content;
		add(content, BorderLayout.CENTER);
	}
	
	protected final Controller controller() {
		return controller;
	}
	
	public String getCardName() {
		return cardName;
	}
	
}
