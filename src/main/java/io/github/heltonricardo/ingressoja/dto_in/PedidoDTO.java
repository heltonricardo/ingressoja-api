package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class PedidoDTO {

  private String numeroCartao;
  private String codigoCartao;
  private String nomeTitular;
  private String cpfTitular;

  private Long idEvento;
  private Long idComprador;

  @OneToMany
  private List<ItemPedidoDTO> itensPedido;

  public Pedido paraObjeto() {
    List<ItemPedido> itens = new ArrayList<>();

    itensPedido.forEach(i -> itens.add(i.paraObjeto()));

    return new Pedido(new Date(), numeroCartao, codigoCartao,
        nomeTitular, cpfTitular, itens, idComprador, idEvento);
  }
}
