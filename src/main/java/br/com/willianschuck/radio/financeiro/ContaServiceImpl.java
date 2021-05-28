package br.com.willianschuck.radio.financeiro;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.model.Conta;

public class ContaServiceImpl extends AbstractCrudService<Conta> implements ContaService {

	public ContaServiceImpl() {
		super(new ContaDAOImpl());
	}

	@Override
	public Conta create() {
		return new Conta();
	}

}
