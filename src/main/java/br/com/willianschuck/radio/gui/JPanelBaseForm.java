package br.com.willianschuck.radio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.willianschuck.radio.control.Actions;
import br.com.willianschuck.radio.control.Controller;
import br.com.willianschuck.radio.model.Entidade;
import br.com.willianschuck.radio.service.Service;

public abstract class JPanelBaseForm<T extends Entidade> extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private T item;
	
	private Actions<T> actions;
	
	private Service<T> service;

	private JButton btnSalvar;

	public JPanelBaseForm(Controller controller, String cardName, Service<T> service) {
		super(controller, cardName);
		this.service = service;
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		addToolbarItem(btnSalvar);
		
	}
	protected abstract T montarObjeto(T item);
	
	protected abstract void preencherFormulario(T obj);
	
	public final T getItem() {
		item = montarObjeto(item);
		return item;
	}
	
	protected void salvar() {
		try {
			T i = getItem();
			service.validar(i);
			service.cadastrar(i);
			actions.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro durante o cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void editar(T obj) {
		preencherFormulario(obj);
		item = obj;
		controller().viewCard(getCardName());
	}
	
	public void setActions(Actions<T> actions) {
		this.actions = actions;
	}
	
	public Actions<T> getActions() {
		return actions;
	}
	
}
