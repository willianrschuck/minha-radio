package br.com.willianschuck.radio.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	
	private class JPanelUsuario extends JPanel {
		private static final long serialVersionUID = 1L;

		public JPanelUsuario() {
			initComponents();
		}
		
		private void initComponents() {
			
			setLayout(new GridBagLayout());
			
			try {
				Image image = ImageIO.read(ClassLoader.getSystemResource("images/avatar.jpg")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				PanelUtil.addToSidebar(this, new JLabel(new ImageIcon(image)), 0, 0, 1, 0, 0);
				
				JLabel lblUsuario = new JLabel("Usuário");
				lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
				lblUsuario.setVerticalAlignment(JLabel.TOP);
				lblUsuario.setHorizontalAlignment(JLabel.CENTER);
				lblUsuario.setForeground(Color.WHITE);
				
				PanelUtil.addToSidebar(this, lblUsuario, 1, 0, 1, 1, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			setOpaque(false);
			setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
			
		}
		
	}
	
	private JPanel pnlUser;

	public JPanelMenuLateral(Controller controller) {
		
		pnlUser = new JPanelUsuario();
		setLayout(new GridBagLayout());
		setBackground(Colors.getDarkBackgroundColor());
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(200, 200, 200)));
		
		PanelUtil.addToSidebar(this, pnlUser, 0, y++, 1, 0, 0);
		PanelUtil.addToSidebar(this, new JSeparator(JSeparator.VERTICAL), 0, y++, 1, 1, 0);
		PanelUtil.addToSidebar(this, new JLabel(""), 0, 99, 1, 0, 1);
		
	}
	
	public void addButton(JButton btn) {
		PanelUtil.addToSidebar(this, btn, 0, y++, 1, 1, 0);
	}
	
	@Override
	public Dimension getPreferredSize() {
		Dimension originSize = super.getPreferredSize();
		return new Dimension(Math.max(200, (int) originSize.getWidth()), (int) originSize.getHeight());
	}
	
}
