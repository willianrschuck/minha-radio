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
	private JComponent contentPane;
	
	public JPanelBase(Controller controller) {
		this.controller = controller;
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
	
	public final void setContentPane(JComponent contentPane) {
		
		if (this.contentPane == null) {
			updateContentPane(contentPane);
		} else {
			remove(this.contentPane);
			updateContentPane(contentPane);
		}
		
	}

	private void updateContentPane(JComponent contentPane) {
		this.contentPane = contentPane;
		add(contentPane, BorderLayout.CENTER);
	}
	
	protected final Controller controller() {
		return controller;
	}
	
}
