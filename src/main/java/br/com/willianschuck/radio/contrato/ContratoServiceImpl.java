package br.com.willianschuck.radio.contrato;

import java.util.Date;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.model.Contrato;

public class ContratoServiceImpl extends AbstractCrudService<Contrato> {

	public ContratoServiceImpl() {
		super(new ContratoRepositoryImpl());
	}
	
	public Contrato create() {
		
		Contrato contrato = new Contrato();
		contrato.setDataInicio(new Date());
		return contrato;
		
	}
	
}
