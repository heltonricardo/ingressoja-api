package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
public class ItemPedidoDTO {

  @NotBlank
  @Size(max = 255)
  private String ingressante;

  @CPF
  private String cpf;

  @NotNull
  @PositiveOrZero
  private Long idTipoDeIngresso;

  public ItemPedido paraObjeto() {
    return new ItemPedido(ingressante, cpf, idTipoDeIngresso);
  }
}
