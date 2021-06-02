package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategoriaIngresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@OneToMany
	private List<TipoDeIngresso> tiposDeIngresso;
	
	/**************************** GETTERS E SETTERS *****************************/
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

	public List<TipoDeIngresso> getTiposDeIngresso() {
		return tiposDeIngresso;
	}

	public void setTiposDeIngresso(List<TipoDeIngresso> tiposDeIngresso) {
		this.tiposDeIngresso = tiposDeIngresso;
	}
}
