package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

@Getter
public class CompradorDTO {

	private String nome;
	private String email;
	private String cpf;

	private String senha;

	public Comprador paraObjeto() {
		return new Comprador(nome, email, cpf, new Usuario(senha));
	}
}
