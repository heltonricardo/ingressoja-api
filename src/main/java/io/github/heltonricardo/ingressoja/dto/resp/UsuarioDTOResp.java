package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioDTOResp {
	private final Long id;
	
	public static UsuarioDTOResp paraDTO(Usuario usuario) {
		return new UsuarioDTOResp(usuario.getId());
	}
}
