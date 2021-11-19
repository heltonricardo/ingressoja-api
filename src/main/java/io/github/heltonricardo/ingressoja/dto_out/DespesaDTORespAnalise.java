package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Despesa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DespesaDTORespAnalise {

  private final String descricao;
  private final Double valor;

  public static DespesaDTORespAnalise paraDTO(Despesa despesa) {

    return new DespesaDTORespAnalise(
        despesa.getDescricao(), despesa.getValor());
  }
}
