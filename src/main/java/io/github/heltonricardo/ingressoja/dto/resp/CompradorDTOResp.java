package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Comprador;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompradorDTOResp {
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private UsuarioDTOResp usuario;
	
	public static CompradorDTOResp paraDTO(Comprador comprador) {
		return new CompradorDTOResp(comprador.getId(), comprador.getNome(),
				comprador.getEmail(), comprador.getCpf(),
				UsuarioDTOResp.paraDTO(comprador.getUsuario()));
	}
}
