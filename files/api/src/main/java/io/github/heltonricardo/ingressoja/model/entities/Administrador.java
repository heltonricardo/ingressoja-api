package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;

@Entity
public class Administrador {

	private Long id;
	private String nome;
	private String email;
	private String banco;
	private String agencia;
	private String conta;
	private Boolean ativo;
}
