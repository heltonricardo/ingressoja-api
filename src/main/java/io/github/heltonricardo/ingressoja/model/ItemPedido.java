package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String ingressante;
  private String cpf;

  @ManyToOne
  private TipoDeIngresso tipoDeIngresso;

  public ItemPedido(String ingressante, String cpf,
                    TipoDeIngresso tipoDeIngresso) {
    this.ingressante = ingressante;
    this.cpf = cpf;
    this.tipoDeIngresso = tipoDeIngresso;
  }
}
