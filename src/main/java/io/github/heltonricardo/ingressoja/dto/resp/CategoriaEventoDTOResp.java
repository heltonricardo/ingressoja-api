package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaEventoDTOResp {

	private Long id;
	private String nome;

	public static CategoriaEventoDTOResp paraDTO(
			CategoriaEvento categoriaEvento) {
		return new CategoriaEventoDTOResp(categoriaEvento.getId(),
				categoriaEvento.getNome());
	}
}
