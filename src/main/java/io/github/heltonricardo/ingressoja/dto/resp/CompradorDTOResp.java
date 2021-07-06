package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Comprador;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompradorDTOResp {
	
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	
	public static CompradorDTOResp paraDTO(Comprador comprador) {
		return new CompradorDTOResp(comprador.getId(), comprador.getNome(),
				comprador.getEmail(), comprador.getCpf());
	}
}
