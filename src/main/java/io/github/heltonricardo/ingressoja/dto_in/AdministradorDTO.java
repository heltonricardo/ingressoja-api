package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

@Getter
public class AdministradorDTO {

	private String nome;
	private String email;
	private String senha;

	public Administrador paraObjeto() {
		return new Administrador(nome, email, new Usuario(senha));
	}
}
