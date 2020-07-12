package br.com.willianschuck.radio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "contrato")
public class Contrato extends Entidade implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_contrato", sequenceName = "seq_contrato_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_contrato", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "emissora_id", nullable = false)
	private Emissora emissora;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "prazo")
	private Integer prazo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datainicio")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "datafinal")
	private Date dataFinal;
	
	@Column(name = "precomensal", precision = 5, columnDefinition = "decimal(20,5)", nullable = false)
	private Double precoMensal;
	
	@Column(name = "discriminacao")
	private String discriminacaoInsercoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Emissora getEmissora() {
		return emissora;
	}

	public void setEmissora(Emissora radio) {
		this.emissora = radio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Double getPrecoMensal() {
		return precoMensal;
	}

	public void setPrecoMensal(Double precoMensal) {
		this.precoMensal = precoMensal;
	}

	public String getDiscriminacaoInsercoes() {
		return discriminacaoInsercoes;
	}

	public void setDiscriminacaoInsercoes(String discriminacaoInsercoes) {
		this.discriminacaoInsercoes = discriminacaoInsercoes;
	}

}
