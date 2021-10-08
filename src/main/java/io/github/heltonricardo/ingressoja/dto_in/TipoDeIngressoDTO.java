package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
public class TipoDeIngressoDTO {

  @NotBlank
  @Size(max = 30)
  private String nome;

  @DecimalMin("0.0")
  private BigDecimal valor;

  @Size(max = 50)
  private String descricao;

  @NotNull
  @Positive
  private Integer quantidadeDisponivel;

  @Future
  @NotNull
  private Date inicio;

  @Future
  @NotNull
  private Date termino;

  public TipoDeIngresso paraObjeto() {
    return new TipoDeIngresso(nome, valor.doubleValue(), descricao,
        quantidadeDisponivel, inicio, termino);
  }
}
