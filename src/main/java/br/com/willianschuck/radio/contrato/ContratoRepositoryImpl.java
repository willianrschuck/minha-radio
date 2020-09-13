package br.com.willianschuck.radio.contrato;

import br.com.willianschuck.base.AbstractDAO;
import br.com.willianschuck.radio.model.Contrato;

class ContratoRepositoryImpl extends AbstractDAO<Contrato> {

	@Override
	public Class<Contrato> getEntityClass() {
		return Contrato.class;
	}

}
