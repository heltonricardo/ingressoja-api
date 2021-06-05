package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class ContaCaixaAdm {
	@Id
	private Integer id = 1;
	private Double valor = 0.0;
	private String banco;
	private String agencia;
	private String conta;
}
