package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Saque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date dataHora;

  @Column(nullable = false)
  private Double valor;
}
