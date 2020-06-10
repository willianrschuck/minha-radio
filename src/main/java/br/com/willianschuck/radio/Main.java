package br.com.willianschuck.radio;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.com.willianschuck.radio.control.Controller;

public class Main {
	
	private Main() {
		new Controller();
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Main();
	}

}
