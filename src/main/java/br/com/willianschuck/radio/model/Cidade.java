package br.com.willianschuck.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado", nullable = false)
	private Estado estado;
	
	public Cidade() {
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Cidade)) {
			return false;
		}
		
		Cidade cidade = (Cidade) obj;
		return id == cidade.id;
		
	}

}
