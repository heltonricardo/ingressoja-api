package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Date dataHora;

  @Column(length = 19, nullable = false)
  private String numeroCartao;

  @Column(length = 4, nullable = false)
  private String codigoCartao;

  @Column(nullable = false)
  private String nomeTitular;

  @Column(length = 11, nullable = false)
  private String cpfTitular;

  @Column(nullable = false)
  private Double valorTotal;

  @ManyToOne(cascade = CascadeType.ALL)
  private Evento evento;

  @ManyToOne(cascade = CascadeType.ALL)
  private Comprador comprador;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ItemPedido> itensPedido;

  @Transient
  private Long idEvento;

  @Transient
  private Long idComprador;

  public Pedido(Date dataHora, String numeroCartao,
                String codigoCartao, String nomeTitular,
                String cpfTitular, List<ItemPedido> itensPedido,
                Long idComprador, Long idEvento) {
    this.dataHora = dataHora;
    this.numeroCartao = numeroCartao;
    this.codigoCartao = codigoCartao;
    this.nomeTitular = nomeTitular;
    this.cpfTitular = cpfTitular;
    this.itensPedido = itensPedido;
    this.idComprador = idComprador;
    this.idEvento = idEvento;
  }
}
