package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoDeIngressoDTORespItemPedido {

  private final String nome;
  private final Double valor;
  private final String descricao;

  public static TipoDeIngressoDTORespItemPedido paraDTO(
      TipoDeIngresso tipoDeIngresso) {
    return new TipoDeIngressoDTORespItemPedido(tipoDeIngresso.getNome(),
        tipoDeIngresso.getValor(),
        tipoDeIngresso.getDescricao());
  }
}
