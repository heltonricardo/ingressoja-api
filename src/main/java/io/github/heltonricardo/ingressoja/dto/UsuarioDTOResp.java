package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioDTOResp {
	private Long id;
	
	public static UsuarioDTOResp paraDTO(Usuario usuario) {
		return new UsuarioDTOResp(usuario.getId());
	}
}
