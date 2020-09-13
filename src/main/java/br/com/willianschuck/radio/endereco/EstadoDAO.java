package br.com.willianschuck.radio.endereco;

import java.util.List;

import br.com.willianschuck.base.AbstractDAO;
import br.com.willianschuck.radio.model.Estado;

public class EstadoDAO extends AbstractDAO<Estado> {

	@Override
	public Class<Estado> getEntityClass() {
		return Estado.class;
	}
	
	public List<Estado> getAll() {
		return super.entityManager().createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
	}
	
}
