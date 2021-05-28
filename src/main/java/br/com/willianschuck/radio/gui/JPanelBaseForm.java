package br.com.willianschuck.radio.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.base.Validator;
import br.com.willianschuck.radio.Controller;
import br.com.willianschuck.radio.model.Entidade;

public abstract class JPanelBaseForm<T extends Entidade> extends JPanelBase {
	private static final long serialVersionUID = 1L;
	
	private T item;
	
	private Actions<T> actions;
	
	private AbstractCrudService<T> service;
	private Validator<T> validator;

	private JButton btnSalvar;

	public JPanelBaseForm(Controller controller, String cardName, AbstractCrudService<T> service, Validator<T> validator) {
		super(controller, cardName);
		this.service = service;
		this.validator = validator;
	}
	
	@Override
	protected void initComponents() {
		super.initComponents();
		
		btnSalvar = ComponentFactory.makeButton(Icons.Save);
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
		if (item == null) {
			item = service.create();
		}
		item = montarObjeto(item);
		return item;
	}
	
	protected void salvar() {
		try {
			T i = getItem();
			validator.validate(i);
			service.cadastrar(i);
			actions.listar();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro durante o cadastro", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public void editar(T item) {
		if (item == null) {
			throw new RuntimeException("O item a ser editado não pode ser nulo.");
		}
		preencherFormulario(item);
		this.item = item;
		controller().viewCard(getCardName());
	}
	
	public void setActions(Actions<T> actions) {
		this.actions = actions;
	}
	
	public Actions<T> getActions() {
		return actions;
	}
	
}
