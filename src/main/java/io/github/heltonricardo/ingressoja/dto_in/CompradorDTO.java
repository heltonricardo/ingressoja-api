package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Comprador;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CompradorDTO {

  @NotBlank
  @Size(max = 255)
  private String nome;

  @CPF
  @NotNull
  private String cpf;

  @Valid
  private UsuarioDTO usuario;

  public Comprador paraObjeto() {
    return new Comprador(nome, cpf, usuario.paraObjeto());
  }
}
