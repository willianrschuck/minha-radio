package br.com.willianschuck.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "emissora")
public class Emissora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_emissora", sequenceName = "seq_emissora_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_emissora", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nomefantasia", length = 60, nullable = false)
	private String nomeFantasia;
	
	@Column(name = "razaosocial", length = 60, nullable = false)
	private String razaoSocial;
	
	@OneToOne
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	
	@Column(name = "cnpj", length = 14, nullable = false)
	private String cnpj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
