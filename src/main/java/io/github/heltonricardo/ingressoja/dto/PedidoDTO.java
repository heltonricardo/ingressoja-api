package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoDTO {

  private LocalDateTime dataHora;
  private String numeroCartao;
  private String codigoSegurancaCartao;
  private String nomeCartao;
  private String cpfCartao;
  private Double valorTotal;

  @OneToMany
  private List<ItemPedidoDTO> itensPedido;

  public Pedido paraObjeto() {
    List<ItemPedido> itens = new ArrayList<>();

    itensPedido.forEach(i -> itens.add(i.paraObjeto()));

    return new Pedido(dataHora, numeroCartao, codigoSegurancaCartao,
        nomeCartao, cpfCartao, valorTotal, itens);
  }
}
