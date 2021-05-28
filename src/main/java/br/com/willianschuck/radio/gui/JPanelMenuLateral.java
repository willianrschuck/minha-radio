package br.com.willianschuck.radio.gui;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.util.PanelUtil;

public class JPanelMenuLateral extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int y = 0;

	public JPanelMenuLateral(Controller controller) {

		setLayout(new GridBagLayout());

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		PanelUtil.addToSidebar(this, new JSeparator(JSeparator.VERTICAL), 0, y++, 1, 1, 0);
		PanelUtil.addToSidebar(this, new JLabel(""), 0, 99, 1, 0, 1);
		
	}
	
	public void addButton(JButton btn) {
		PanelUtil.addToSidebar(this, btn, 0, y++, 1, 1, 0);
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension originSize = super.getPreferredSize();
		return originSize;// new Dimension(Math.max(75, (int) originSize.getWidth()), (int) originSize.getHeight());
	}
	
}
