package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.model.Pedido;
import lombok.Getter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class PedidoDTO {

  @CreditCardNumber
  private String numeroCartao;

  @NotBlank
  private String codigoCartao;

  @NotBlank
  @Size(max = 255)
  private String nomeTitular;

  @CPF
  private String cpfTitular;

  @NotNull
  @PositiveOrZero
  private Long idEvento;

  @NotNull
  @PositiveOrZero
  private Long idComprador;

  @NotEmpty
  @OneToMany
  private List<@Valid ItemPedidoDTO> itensPedido;

  public Pedido paraObjeto() {
    List<ItemPedido> itens = new ArrayList<>();

    itensPedido.forEach(i -> itens.add(i.paraObjeto()));

    return new Pedido(new Date(), numeroCartao, codigoCartao,
        nomeTitular, cpfTitular, itens, idComprador, idEvento);
  }
}
