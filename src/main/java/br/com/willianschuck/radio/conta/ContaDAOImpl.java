package br.com.willianschuck.radio.conta;

import br.com.willianschuck.base.AbstractDAO;
import br.com.willianschuck.radio.model.Conta;

public class ContaDAOImpl extends AbstractDAO<Conta> {

	@Override
	public Class<Conta> getEntityClass() {
		return Conta.class;
	}
	
}
