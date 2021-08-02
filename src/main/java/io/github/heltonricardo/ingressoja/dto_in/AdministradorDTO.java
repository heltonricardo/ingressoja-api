package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Administrador;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class AdministradorDTO {

  @NotBlank
  @Size(max = 255)
  private String nome;

  @Valid
  private UsuarioDTO usuario;

  public Administrador paraObjeto() {
    return new Administrador(nome, usuario.paraObjeto());
  }
}
