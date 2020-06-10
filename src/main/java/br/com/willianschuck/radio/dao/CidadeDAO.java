package br.com.willianschuck.radio.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Estado;

public class CidadeDAO extends DAO<Cidade> {

	@Override
	public Class<Cidade> getEntityClass() {
		return Cidade.class;
	}

	public List<Cidade> getFrom(Estado estado) {
		
		TypedQuery<Cidade> query = entityManager().createQuery("SELECT c FROM Cidade c WHERE c.estado = :pEstado", Cidade.class);
		query.setParameter("pEstado", estado);
		return query.getResultList();
		
	}
	
}
