package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTORespEvento {

	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;

	public static ProdutoraDTORespEvento paraDTO(Produtora produtora) {
		return new ProdutoraDTORespEvento(produtora.getId(),
				produtora.getEmail(), produtora.getNomeFantasia(),
				produtora.getRazaoSocial(), produtora.getCnpj());
	}
}
