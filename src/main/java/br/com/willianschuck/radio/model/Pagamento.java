package br.com.willianschuck.radio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pagamento extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Conta conta;
	private Double valor;
	
	public Pagamento() {
	}
	
	public Pagamento(Conta conta, Double valor) {
		this.conta = conta;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
