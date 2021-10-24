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
  private final String publicToken;

  public static ProdutoraDTOResp paraDTO(Produtora produtora) {
    return new ProdutoraDTOResp(produtora.getId(),
        produtora.getUsuario().getEmail(), produtora.getNomeFantasia(),
        produtora.getRazaoSocial(), produtora.getCnpj(),
        produtora.getPublicToken());
  }
}
