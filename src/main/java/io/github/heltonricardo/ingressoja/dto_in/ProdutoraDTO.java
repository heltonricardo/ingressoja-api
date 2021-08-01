package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ProdutoraDTO {

  @NotNull
  @Email
  private String email;

  @NotBlank
  @Size(max = 255)
  private String nomeFantasia;

  @NotBlank
  @Size(max = 255)
  private String razaoSocial;

  @CNPJ
  private String cnpj;

  @NotBlank
  @Size(max = 50)
  private String banco;

  @NotBlank
  @Size(max = 20)
  private String agencia;

  @NotBlank
  @Size(max = 20)
  private String conta;

  @Valid
  private UsuarioDTO usuario;

  public Produtora paraObjeto() {
    return new Produtora(email, nomeFantasia, razaoSocial, cnpj, banco,
        agencia, conta, usuario.paraObjeto());
  }

}
