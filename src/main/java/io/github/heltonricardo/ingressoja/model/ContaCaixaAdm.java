package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

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
