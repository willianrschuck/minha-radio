package br.com.willianschuck.radio.contrato;

import java.util.Date;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.financeiro.ContaService;
import br.com.willianschuck.radio.model.Contrato;

public class ContratoServiceImpl extends AbstractCrudService<Contrato> {

	private ContaService contaService;
	
	public ContratoServiceImpl() {
		super(new ContratoRepositoryImpl());
	}
	
	@Override
	public void cadastrar(Contrato e) {
		super.cadastrar(e);
	}
	
	public Contrato create() {
		
		Contrato contrato = new Contrato();
		contrato.setDataInicio(new Date());
		return contrato;
		
	}
	
}
