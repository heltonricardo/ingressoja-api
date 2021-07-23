package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.Getter;

@Getter
public class ProdutoraDTO {

  private String email;
  private String nomeFantasia;
  private String razaoSocial;
  private String cnpj;
  private String banco;
  private String agencia;
  private String conta;
  private UsuarioDTO usuario;

  public Produtora paraObjeto() {
    return new Produtora(email, nomeFantasia, razaoSocial, cnpj, banco,
        agencia, conta, usuario.paraObjeto());
  }

}
