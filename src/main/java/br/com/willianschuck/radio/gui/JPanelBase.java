package br.com.willianschuck.radio.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import br.com.willianschuck.radio.Controller;

public abstract class JPanelBase extends JPanel {
	private static final long serialVersionUID = 1L;

	private Controller controller;
	
	private JToolBar toolBar;
	private JPanel pnlTopo;
	private JComponent content;
	
	private String cardName;

	private JLabel lblScreenName;
	
	public JPanelBase(Controller controller, String cardName) {
		this.controller = controller;
		this.cardName = cardName;
	}

	protected void initComponents() {
		
		setLayout(new BorderLayout());
		
		pnlTopo = new JPanel(new BorderLayout());
		pnlTopo.setBackground(Colors.getDarkBackgroundColor());
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		
		lblScreenName = new JLabel(getScreenName());
		lblScreenName.setFont(new Font("Arial", Font.BOLD, 16));
		lblScreenName.setHorizontalAlignment(JLabel.RIGHT);
		lblScreenName.setForeground(Color.WHITE);
		lblScreenName.setBorder(new EmptyBorder(5, 0, 0, 10));
		
		pnlTopo.add(lblScreenName, BorderLayout.NORTH);
		pnlTopo.add(toolBar, BorderLayout.CENTER);
		add(pnlTopo, BorderLayout.NORTH);
		
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
	
	protected abstract String getScreenName();
	
}
