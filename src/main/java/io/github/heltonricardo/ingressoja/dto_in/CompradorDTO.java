package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

@Getter
public class CompradorDTO {

	private String nome;
	private String email;
	private String cpf;
	private UsuarioDTO usuarioDTO;

	public Comprador paraObjeto() {
		return new Comprador(nome, email, cpf, usuarioDTO.paraObjeto());
	}
}
