package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Comprador;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompradorDTOResp {
	
	private final Long id;
	private final String nome;
	private final String email;
	private final String cpf;
	
	public static CompradorDTOResp paraDTO(Comprador comprador) {
		return new CompradorDTOResp(comprador.getId(), comprador.getNome(),
				comprador.getEmail(), comprador.getCpf());
	}
}
