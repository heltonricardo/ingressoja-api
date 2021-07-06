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
  private String email;

  @ManyToOne(cascade = CascadeType.ALL)
  private TipoDeIngresso tipoDeIngresso;

  public ItemPedido(String ingressante, String cpf, String email,
                    TipoDeIngresso tipoDeIngresso) {
    this.ingressante = ingressante;
    this.cpf = cpf;
    this.email = email;
    this.tipoDeIngresso = tipoDeIngresso;
  }
}
