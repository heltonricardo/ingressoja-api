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

  @Column(nullable = false)
  private String ingressante;

  @Column(length = 11, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private Boolean utilizado = false;

  @ManyToOne
  private TipoDeIngresso tipoDeIngresso;

  @ManyToOne
  private Pedido pedido;

  @Transient
  private Long idTipoDeIngresso;

  public ItemPedido(String ingressante, String cpf,
                    Long idTipoDeIngresso) {
    this.ingressante = ingressante;
    this.cpf = cpf;
    this.idTipoDeIngresso = idTipoDeIngresso;
  }

  public void utilizar() {

    this.setUtilizado(true);
  }

  public boolean cpfPertenceAoItem(String cpf) {

    return this.getCpf().equals(cpf);
  }
}
