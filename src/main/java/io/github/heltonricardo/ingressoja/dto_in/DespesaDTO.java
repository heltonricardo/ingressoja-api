package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Despesa;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
public class DespesaDTO {

  @NotBlank
  @Size(max = 255)
  private String descricao;

  @NotNull
  @DecimalMin("0.01")
  private BigDecimal valor;

  public Despesa paraObjeto() {
    return new Despesa(descricao, valor.doubleValue());
  }
}
