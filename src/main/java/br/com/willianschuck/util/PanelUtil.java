package br.com.willianschuck.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelUtil {

	private static GridBagConstraints gbc;
	
	static {
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
	}
	
	private PanelUtil() {
	}

	public static void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan, int weightx, int weighty) {
		
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = colspan;
		
		panel.add(component, gbc);
		
	}
	
	public static void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan) {
		addToPanel(panel, component, x, y, colspan, 0, 0);
	}
	
}
