package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTORespEvento {

	private final Long id;
	private final String email;
	private final String nomeFantasia;
	private final String razaoSocial;
	private final String cnpj;

	public static ProdutoraDTORespEvento paraDTO(Produtora produtora) {
		return new ProdutoraDTORespEvento(produtora.getId(),
				produtora.getEmail(), produtora.getNomeFantasia(),
				produtora.getRazaoSocial(), produtora.getCnpj());
	}
}
