package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Administrador;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdministradorDTOResp {

  private final Long id;
  private final String nome;
  private final String email;

  public static AdministradorDTOResp paraDTO(Administrador administrador) {
    return new AdministradorDTOResp(administrador.getId(),
        administrador.getNome(), administrador.getEmail());
  }
}
