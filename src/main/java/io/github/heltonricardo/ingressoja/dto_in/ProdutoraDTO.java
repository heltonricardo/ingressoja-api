package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.model.Usuario;
import lombok.Getter;

@Getter
public class ProdutoraDTO {

	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String banco;
	private String agencia;
	private String conta;
	private UsuarioDTO usuarioDTO;

	public Produtora paraObjeto() {
		return new Produtora(email, nomeFantasia, razaoSocial, cnpj, banco,
				agencia, conta, usuarioDTO.paraObjeto());
	}

}
