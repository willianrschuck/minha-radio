package br.com.willianschuck.radio.gui;

import javax.swing.ImageIcon;

public class Icons {

	private static ImageIcon newIcon = new ImageIcon(ClassLoader.getSystemResource("icons/new_white.png"));
	private static ImageIcon saveIcon = new ImageIcon(ClassLoader.getSystemResource("icons/save_white.png"));
	private static ImageIcon editIcon = new ImageIcon(ClassLoader.getSystemResource("icons/edit_white.png"));
	private static ImageIcon deleteIcon = new ImageIcon(ClassLoader.getSystemResource("icons/delete_white.png"));
	private static ImageIcon pdfIcon = new ImageIcon(ClassLoader.getSystemResource("icons/pdf_white.png"));
	
	public static ImageIcon getNewIcon() {
		return newIcon;
	}

	public static ImageIcon getSaveIcon() {
		return saveIcon;
	}
	
	public static ImageIcon getEditIcon() {
		return editIcon;
	}
	
	public static ImageIcon getDeleteIcon() {
		return deleteIcon;
	}
	
	public static ImageIcon getPdfIcon() {
		return pdfIcon;
	}
	
}
