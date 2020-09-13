package br.com.willianschuck.radio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pagamento extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	public Integer getId() {
		return id;
	}
	
}
