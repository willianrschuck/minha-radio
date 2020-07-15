package br.com.willianschuck.radio.gui.lista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.report.ContratoReport;
import br.com.willianschuck.radio.service.ContratoService;
import br.com.willianschuck.radio.to.ContratoTO;

public class JPanelContratoLista extends JPanelBaseList<Contrato> {
	private static final long serialVersionUID = 1L;
	
	private ContratoService contratoService;
	
	private JButton btnImprimir;
	
	public JPanelContratoLista(Controller controller) {
		
		super(controller, "contrato_lista", controller.getContratoService());
		contratoService = controller.getContratoService();
		
		initComponents();
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirContratoSelecionado();
			}
		});
		btnImprimir.setEnabled(false);
		addToolbarItem(btnImprimir);
		
	}

	private void imprimirContratoSelecionado() {
		
		ContratoTO contratoTO = contratoService.generateContratoTO(getItemSelecionado());
		ContratoReport contratoReport = new ContratoReport();
		
		byte[] bytea = contratoReport.generate(contratoTO);
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Documentos PDF", "pdf"));
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			FileOutputStream os;
			try {
				os = new FileOutputStream(fileToSave);
				os.write(bytea);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Cliente", "Início", "Término", "Valor Mensal"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Contrato contrato : contratoService.getAll()) {
			data.add(new Object[] {contrato.getId(), contrato.getCliente().getNome(), contrato.getDataInicio(), contrato.getDataFinal(), contrato.getPrecoMensal()});
		}
		return data;
		
	}
	
	@Override
	protected void updateToolbarButtons() {
		super.updateToolbarButtons();
		boolean hasItemSelecionado = hasItemSelecionado();
		btnImprimir.setEnabled(hasItemSelecionado);
	}

}
