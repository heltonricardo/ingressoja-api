package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;

@Entity
public class Organizadora {

	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private Double valorCarteira;
	private String banco;
	private String agencia;
	private String conta;
	private Boolean ativo;
}
