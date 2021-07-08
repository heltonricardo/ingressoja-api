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
  private Date dataHora;
  private String numeroCartao;
  private String codigoSegurancaCartao;
  private String nomeTitular;
  private String cpfTitular;
  private Double valorTotal;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ItemPedido> itensPedido;

  @Transient
  private Long idEvento;

  @Transient
  private Long idComprador;

  public Pedido(Date dataHora, String numeroCartao,
                String codigoSegurancaCartao, String nomeTitular,
                String cpfTitular, Double valorTotal,
                List<ItemPedido> itensPedido, Long idComprador, Long idEvento) {
    this.dataHora = dataHora;
    this.numeroCartao = numeroCartao;
    this.codigoSegurancaCartao = codigoSegurancaCartao;
    this.nomeTitular = nomeTitular;
    this.cpfTitular = cpfTitular;
    this.valorTotal = valorTotal;
    this.itensPedido = itensPedido;
    this.idComprador = idComprador;
    this.idEvento = idEvento;
  }
}
