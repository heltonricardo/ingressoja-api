package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL)
	List<Saque> saques;

	public void addSaque(Saque saque) {
		saques.add(saque);
	}
}
