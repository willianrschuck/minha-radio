package br.com.willianschuck.radio.gui;

import javax.swing.table.DefaultTableModel;

public class UneditableDefaultTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public UneditableDefaultTableModel(Object[] objects, int i) {
		super(objects, i);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
