package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Despesa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DespesaDTOResp {

  private final Long id;
  private final String descricao;
  private final Double valor;

  public static DespesaDTOResp paraDTO(Despesa despesa) {

    return new DespesaDTOResp(
        despesa.getId(), despesa.getDescricao(), despesa.getValor());
  }
}
