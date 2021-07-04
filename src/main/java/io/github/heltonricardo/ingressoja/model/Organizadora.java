package io.github.heltonricardo.ingressoja.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "ativo")
public class Organizadora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;

	private String banco;
	private String agencia;
	private String conta;

	private Double valorCarteira = 0.0;
	private Boolean ativo = true;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "organizadora")
	@JsonManagedReference
	private List<Evento> eventos;

	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Saque> saques;

	public Organizadora(String email, String nomeFantasia, String razaoSocial,
			String cnpj, String banco, String agencia, String conta,
			Usuario usuario) {
		this.email = email;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.usuario = usuario;
	}

	public void adicionaEvento(Evento evento) {
		eventos.add(evento);
	}
}
