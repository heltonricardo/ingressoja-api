package io.github.heltonricardo.ingressoja.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity  @Getter @Setter
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne
	private Organizadora organizadora;
	
	@OneToMany
	private List<TipoDeIngresso> tiposDeIngresso;
}
