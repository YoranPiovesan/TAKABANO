package br.com.takabano.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -4045329195254922699L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "num_documento")
	private String numDocumento;
	
	public Cliente(String nome) {
		super();
		this.nome = nome;
	}
	
	
	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	

	public Cliente(Long id, String nome, String numDocumento) {
		super();
		this.id = id;
		this.nome = nome;
		this.numDocumento = numDocumento;
	}
	
	public Cliente(String nome, String numDocumento) {
		super();
		this.nome = nome;
		this.numDocumento = numDocumento;
	}


	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
