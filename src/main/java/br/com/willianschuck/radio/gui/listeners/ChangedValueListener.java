package br.com.willianschuck.radio.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ChangedValueListener implements DocumentListener {

	private Timer timer;
	private List<ChangeListener> listeners;
	
	public ChangedValueListener() {
		
		listeners = new ArrayList<ChangeListener>(10);
		timer = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notifyListeners();
			}
		});
		timer.setRepeats(false);
		
	}
	
	private void notifyListeners() {
		
		ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(e);
		}
		
	}
	
	public void addChangeListener(ChangeListener c) {
		listeners.add(c);
	}
	
	public void removeChangeListener(ChangeListener c) {
		listeners.remove(c);
	}
	
	public void insertUpdate(DocumentEvent e) {
		timer.restart();
	}

	public void removeUpdate(DocumentEvent e) {
		timer.restart();
	}

	public void changedUpdate(DocumentEvent e) {
		timer.restart();
	}

}
