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

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.base.ReportService;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.gui.ComponentFactory;
import br.com.willianschuck.radio.gui.Icons;
import br.com.willianschuck.radio.gui.JPanelBaseList;
import br.com.willianschuck.radio.model.Contrato;

public class JPanelContratoLista extends JPanelBaseList<Contrato> {
	private static final long serialVersionUID = 1L;
	
	private AbstractCrudService<Contrato> contratoService;
	private ReportService<Contrato> contratoReportService;
	
	private JButton btnImprimir;
	
	public JPanelContratoLista(Controller controller, AbstractCrudService<Contrato> contratoService, 
			ReportService<Contrato> contratoReportService) {
		
		super(controller, "contrato_lista", contratoService);
		
		this.contratoService = contratoService;
		this.contratoReportService = contratoReportService;
		
		initComponents();
		
		btnImprimir = ComponentFactory.makeButton("Gerar PDF", Icons.getPdfIcon());
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirContratoSelecionado();
			}
		});
		btnImprimir.setEnabled(false);
		addToolbarItem(btnImprimir);
		
	}

	private void imprimirContratoSelecionado() {
		showSaveFileScreen(contratoReportService.makeReport(getItemSelecionado()));
	}
	
	private void showSaveFileScreen(byte[] data) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Salvar");
		fileChooser.setFileFilter(new FileNameExtensionFilter("Documentos PDF", "pdf"));
		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			FileOutputStream os;
			try {
				os = new FileOutputStream(fileToSave);
				os.write(data);
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	protected DefaultTableModel getDefaultTableModel() {
		return new DefaultTableModel(new Object[] {"Cód.", "Cliente", "Início", "Término", "Valor Mensal", "Valor Total"}, 0);
	}
	
	@Override
	protected List<Object[]> getData() {
		
		List<Object[]> data = new ArrayList<Object[]>();
		for (Contrato contrato : contratoService.getAll()) {
			data.add(new Object[] {contrato.getId(), contrato.getCliente().getNomeFantasia(), contrato.getDataInicio(), contrato.getDataFinal(), contrato.getPrecoMensal(), contrato.getValorTotal()});
		}
		return data;
		
	}
	
	@Override
	protected void updateToolbarButtons() {
		super.updateToolbarButtons();
		boolean hasItemSelecionado = hasItemSelecionado();
		btnImprimir.setEnabled(hasItemSelecionado);
	}
	
	@Override
	protected String getScreenName() {
		return "Lista de Contratos";
	}

}
