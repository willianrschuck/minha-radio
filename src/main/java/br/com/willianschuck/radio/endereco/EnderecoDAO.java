package br.com.willianschuck.radio.endereco;

import br.com.willianschuck.base.AbstractDAO;
import br.com.willianschuck.radio.model.Endereco;

class EnderecoDAO extends AbstractDAO<Endereco> {

	@Override
	public Class<Endereco> getEntityClass() {
		return Endereco.class;
	}
	
}
