package br.com.willianschuck.radio.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ComponentFactory {
	
    static final Border border = new EmptyBorder(5, 5, 5, 5);
    static final Border sidebarBorder = new EmptyBorder(7, 15, 7, 15);
    
	private ComponentFactory() {
	}
	
	public static JButton makeSidebarButton(Icon icon) {
		JButton btn = makeButton(icon);
		btn.setFont(new Font("Arial", Font.PLAIN, 16));
		btn.setBorder(sidebarBorder);
		return btn;
	}
	
	public static JButton makeSidebarButton(String text) {
		JButton btn = makeButton(text);
		btn.setFont(new Font("Arial", Font.PLAIN, 16));
		btn.setBorder(sidebarBorder);
		return btn;
	}
	
	public static JButton makeButton(Icon icon, ActionListener listener) {
		
		JButton btn = makeButton(icon);
		btn.addActionListener(listener);
		return btn;
		
	}
	
	public static JButton makeButton(String text, Icon icon) {
		
		JButton btn = makeButton();
		btn.setText(text);
		btn.setIcon(icon);
		return btn;
		
	}
	
	public static JButton makeButton(String text) {
		
		JButton btn = makeButton();
		btn.setText(text);
		return btn;
		
	}
	
	public static JButton makeButton(Icon icon) {

		JButton btn = makeButton();
		btn.setIcon(icon);
		return btn;
		
	}
	
	public static JButton makeButton() {
		
		JButton btn = new JButton();
		
		btn.setBackground(Colors.getDarkBackgroundColor());
		btn.setForeground(Color.WHITE);
		btn.setOpaque(true);
		btn.setFocusPainted(false);
		btn.setBorder(border);
		btn.setBorderPainted(false);
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if (btn.isEnabled()) {
					btn.setBackground(Colors.getDarkBackgroundColor().brighter());
				}
			}
			
			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				if (btn.isEnabled()) {
					btn.setBackground(Colors.getDarkBackgroundColor());
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (btn.isEnabled()) {
					btn.setBackground(Colors.getDarkBackgroundColor().brighter().brighter());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (btn.isEnabled()) {
					btn.setBackground(Colors.getDarkBackgroundColor().brighter());
				}
			}
			
		});
		
		return btn;
		
	}
	
}
