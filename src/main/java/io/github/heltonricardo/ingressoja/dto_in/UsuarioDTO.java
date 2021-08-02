package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UsuarioDTO {

  @NotBlank
  @Size(min = 6, max = 30)
  private String senha;

  @Email
  @NotNull
  @Size(max = 255)
  private String email;

  public Usuario paraObjeto() {
    return new Usuario(senha, email);
  }
}
