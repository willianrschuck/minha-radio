package br.com.willianschuck.radio.service;

import java.util.Date;

import br.com.willianschuck.radio.dao.ContratoDAO;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.validator.ContratoValidator;

public class ContratoService extends Service<Contrato> {

	public ContratoService() {
		super(new ContratoDAO(), new ContratoValidator());
	}
	
	public Contrato create() {
		
		Contrato contrato = new Contrato();
		contrato.setDataInicio(new Date());
		return contrato;
		
	}
	
}
