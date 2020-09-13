package br.com.willianschuck.radio.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Conta extends Entidade {
	private static final long serialVersionUID = 1L;

	public enum Status {
		ABERTA, FECHADA;
	}

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_conta", sequenceName = "seq_conta_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_conta", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "valor")
	private Double valor;
	
	@Column(name = "valorpago")
	private Double valorPago;
	
	@OneToMany
	private List<Pagamento> pagamentos;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Double getValorPago() {
		return valorPago;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
