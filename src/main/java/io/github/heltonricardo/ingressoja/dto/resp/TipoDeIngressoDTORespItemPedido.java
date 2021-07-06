package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoDeIngressoDTORespItemPedido {

  private String nome;
  private Double valor;
  private String descricao;

  public static TipoDeIngressoDTORespItemPedido paraDTO(
      TipoDeIngresso tipoDeIngresso) {
    return new TipoDeIngressoDTORespItemPedido(tipoDeIngresso.getNome(),
        tipoDeIngresso.getValor(),
        tipoDeIngresso.getDescricao());
  }
}
