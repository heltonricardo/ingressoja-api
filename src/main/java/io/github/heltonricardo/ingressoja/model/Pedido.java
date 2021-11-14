package io.github.heltonricardo.ingressoja.model;

import io.github.heltonricardo.ingressoja.utils.Pagamento;
import io.github.heltonricardo.ingressoja.utils.StatusPedido;
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
  private String statusPagamento = StatusPgto.PENDENTE;

  @Column(length = 50, nullable = false)
  private String statusPedido = StatusPedido.AGUARDANDO_PGTO;

  @ManyToOne
  private Evento evento;

  @ManyToOne
  private Comprador comprador;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
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

  /****************************** CALCULAR TOTAL ******************************/

  public Double calcularTotal() {
    return this.getItensPedido().stream()
        .reduce(0.0, (s, item) ->
            s + item.getTipoDeIngresso().getValor(), Double::sum);
  }

  /**************************** DEVOLVER INGRESSOS ****************************/

  public void devolverIngressos() {
    this.getItensPedido()
        .forEach(i -> i.getTipoDeIngresso().incrementarQntDisp());
  }

  /*********************** IS STATUS PEDIDO PROCESSADO ************************/

  public boolean isStatusPedidoProcessado() {
    return this.getStatusPedido().equals(StatusPedido.PROCESSADO);
  }

  /************************* IS STATUS PGTO APROVADO **************************/
  public boolean isStatusPgtoAprovado() {
    return this.getStatusPagamento().equals(StatusPgto.APROVADO);
  }

  /************************* IS STATUS PGTO PENDENTE **************************/

  public boolean isStatusPgtoPendente() {
    return this.getStatusPagamento().equals(StatusPgto.PENDENTE);
  }

  /************************* IS STATUS PGTO RECUSADO **************************/

  public boolean isStatusPgtoRecusado() {
    return this.getStatusPagamento().equals(StatusPgto.RECUSADO);
  }

  /***************************** IS PEDIDO GRATIS *****************************/

  public boolean isPedidoGratis() {
    return this.calcularTotal().equals(0.0);
  }

  /************************ ATUALIZAR STATUS PAGAMENTO ************************/

  public void atualizarStatusPagamento() {
    String res = Pagamento.consultarPagamento(this);

    if (res != null) {
      this.statusPagamento = res;
    }
  }

  /************************* CALCULAR TAXA PLATAFORMA *************************/

  public Double calcularTaxaPlataforma() {
    return this.calcularTotal() * 0.1;
  }
}
