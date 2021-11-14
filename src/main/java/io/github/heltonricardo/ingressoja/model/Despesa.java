package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Despesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private Double valor;

  @ManyToOne
  private Evento evento;

  public Despesa(String descricao, Double valor) {
    this.descricao = descricao;
    this.valor = valor;
  }

  /***************************** SOMAR NA DESPESA *****************************/

  public void somarNaDespesa(Double valor) {
    this.setValor(this.getValor() + valor);
  }
}
