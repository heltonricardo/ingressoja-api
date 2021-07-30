package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CategoriaEventoDTO {

  @NotBlank
  @Size(max = 50)
  private String nome;

  public CategoriaEvento paraObjeto() {
    return new CategoriaEvento(nome);
  }
}
