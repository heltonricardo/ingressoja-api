package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTOResp {

  private final Long id;
  private final String email;
  private final String nomeFantasia;
  private final String razaoSocial;
  private final String cnpj;
  private final String banco;
  private final String agencia;
  private final String conta;
  private final Double valorCarteira;

  public static ProdutoraDTOResp paraDTO(Produtora produtora) {
    return new ProdutoraDTOResp(produtora.getId(),
        produtora.getEmail(), produtora.getNomeFantasia(),
        produtora.getRazaoSocial(), produtora.getCnpj(),
        produtora.getBanco(), produtora.getAgencia(),
        produtora.getConta(), produtora.getValorCarteira());
  }
}
