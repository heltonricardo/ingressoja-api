package io.github.heltonricardo.ingressoja.model.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Evento {

	private Long id;
	private String nome;
	private String descricao;
	private String imagemURL;
	
	private Date dataInicio;
	private Date dataTerminio;
	private String horarioInicio;  
	private String horarioTermino;
	
	private String url;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
}
