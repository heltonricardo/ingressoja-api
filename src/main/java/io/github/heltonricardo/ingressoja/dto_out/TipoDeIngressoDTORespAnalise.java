package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoDeIngressoDTORespAnalise {

  private final String nome;
  private final Double valor;
  private final Integer quantidadeTotal;
  private final Integer quantidadeVendida;
  private final Double porcentagemVendida;
  private final Double receitaGerada;

  public static TipoDeIngressoDTORespAnalise paraDTO(
      TipoDeIngresso tipoDeIngresso) {

    return new TipoDeIngressoDTORespAnalise(
        tipoDeIngresso.getNome(),
        tipoDeIngresso.getValor(),
        tipoDeIngresso.getQuantidadeTotal(),
        tipoDeIngresso.calcularQntVendida(),
        tipoDeIngresso.calcularPorcentagemVendida(),
        tipoDeIngresso.calcularReceitaGerada()
    );
  }
}
