package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;

@Entity
public class Comprador {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private Boolean ativo;
}
