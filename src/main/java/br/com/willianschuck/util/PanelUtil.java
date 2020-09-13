package br.com.willianschuck.util;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class PanelUtil {

	private static final Insets noMargin = new Insets(0, 0, 0, 0);
	private static final Insets margin = new Insets(5, 5, 5, 5);
	private static GridBagConstraints gbc;
	
	static {
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
	}
	
	private PanelUtil() {
	}

	public static void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan, int weightx, int weighty) {
		
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = colspan;
		gbc.insets = margin;

		panel.add(component, gbc);
		
	}

	public static void addToSidebar(JPanel panel, JComponent component, int x, int y, int colspan, int weightx, int weighty) {
		
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = colspan;
		gbc.insets = noMargin;
		
		panel.add(component, gbc);
		
	}
	
	public static void addToPanel(JPanel panel, JComponent component, int x, int y, int colspan) {
		addToPanel(panel, component, x, y, colspan, 0, 0);
	}
	
}
