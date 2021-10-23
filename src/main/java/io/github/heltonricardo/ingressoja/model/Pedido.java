package io.github.heltonricardo.ingressoja.model;

import io.github.heltonricardo.ingressoja.utils.Pagamento;
import io.github.heltonricardo.ingressoja.utils.StatusPgto;
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

  @Column(nullable = false)
  private Double valorTotal;

  @Column(length = 1000, nullable = false)
  private String urlPagamento;

  @Column(length = 10, nullable = false)
  private String statusPagamento = StatusPgto.IN_PROGRESS;

  @ManyToOne
  private Evento evento;

  @ManyToOne
  private Comprador comprador;

  @OneToMany(cascade = CascadeType.ALL)
  private List<ItemPedido> itensPedido;

  @Transient
  private Long idEvento;

  @Transient
  private Long idComprador;

  public Pedido(Date dataHora, List<ItemPedido> itensPedido,
                Long idComprador, Long idEvento) {
    this.dataHora = dataHora;
    this.itensPedido = itensPedido;
    this.idComprador = idComprador;
    this.idEvento = idEvento;
  }

  public Double calcularTotal() {
    return this.getItensPedido().stream()
        .reduce(0.0, (s, item) ->
            s + item.getTipoDeIngresso().getValor(), Double::sum);
  }

  public void devolverIngressos() {
    this.getItensPedido()
        .forEach(i -> i.getTipoDeIngresso().incrementarQntDisp());
  }

  public boolean isStatusPgtoPendente() {
    return this.getStatusPagamento().equals(StatusPgto.IN_PROGRESS);
  }

  public boolean isStatusPgtoRejeitado() {
    return this.getStatusPagamento().equals(StatusPgto.REJECTED);
  }

  public void atualizaStatusPagamento() {
      this.statusPagamento = Pagamento.consultarPagamento(this);
  }
}
