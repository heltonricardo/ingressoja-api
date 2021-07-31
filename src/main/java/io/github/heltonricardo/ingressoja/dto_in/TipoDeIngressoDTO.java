package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
public class TipoDeIngressoDTO {

  @NotBlank
  @Size(max = 30)
  String nome;

  @DecimalMin("0.0")
  BigDecimal valor;

  @NotBlank
  @Size(max = 100)
  String descricao;

  @NotNull
  @Positive
  Integer quantidadeTotal;

  public TipoDeIngresso paraObjeto() {
    return new TipoDeIngresso(nome, valor.doubleValue(), descricao,
        quantidadeTotal);
  }
}
