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

@Getter @Setter
@Entity @Where(clause="ativo")
@SQLDelete(sql = "UPDATE organizadora SET ativo = false WHERE id = ?")
public class Organizadora {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany
	private List<Saque> saques;
}
