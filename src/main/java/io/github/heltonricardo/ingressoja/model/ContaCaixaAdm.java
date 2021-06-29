package io.github.heltonricardo.ingressoja.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaCaixaAdm {
	@Id
	private Integer id = 1;
	private Double valor = 0.0;
	private String banco;
	private String agencia;
	private String conta;
}
