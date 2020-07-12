package br.com.willianschuck.radio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado_id", allocationSize = 1)
	@GeneratedValue(generator = "seq_estado", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
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
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Estado)) {
			return false;
		}
		
		Estado estado = (Estado) obj;
		return id == estado.id;
		
	}
	
}