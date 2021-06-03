package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity @Where(clause="ativo")
@SQLDelete(sql = "UPDATE administrador SET ativo = false WHERE id = ?")
@Getter @Setter
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String email;
	private String banco;
	private String agencia;
	private String conta;
	private Boolean ativo;

	@OneToMany
	List<Saque> saques;
}
