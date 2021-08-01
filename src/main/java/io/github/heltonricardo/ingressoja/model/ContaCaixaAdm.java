package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ContaCaixaAdm {
  @Id
  private Integer id = 1;

  @Column(nullable = false)
  private Double valor = 0.0;

  @Column(length = 50, nullable = false)
  private String banco;

  @Column(length = 20, nullable = false)
  private String agencia;

  @Column(length = 20, nullable = false)
  private String conta;
}
