package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoriaEventoDTORespSimples {

	private Long id;
	private String nome;

	public static CategoriaEventoDTORespSimples paraDTO(
			CategoriaEvento categoriaEvento) {
		return new CategoriaEventoDTORespSimples(categoriaEvento.getId(),
				categoriaEvento.getNome());
	}
}
