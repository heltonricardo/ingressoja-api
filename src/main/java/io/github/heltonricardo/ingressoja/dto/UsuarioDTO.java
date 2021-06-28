package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {

	private String senha;
	
	public Usuario paraObjeto() {
		return new Usuario(senha);
	}
}
