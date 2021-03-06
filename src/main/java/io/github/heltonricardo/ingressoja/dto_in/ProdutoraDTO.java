package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class ProdutoraDTO {

  @NotBlank
  @Size(max = 255)
  private String nomeFantasia;

  @NotBlank
  @Size(max = 255)
  private String razaoSocial;

  @CNPJ
  private String cnpj;

  @NotBlank
  @Size(max = 45)
  private String publicToken;

  @Valid
  private UsuarioDTO usuario;

  public Produtora paraObjeto() {
    return new Produtora(nomeFantasia, razaoSocial, cnpj, publicToken,
        usuario.paraObjeto());
  }

}
