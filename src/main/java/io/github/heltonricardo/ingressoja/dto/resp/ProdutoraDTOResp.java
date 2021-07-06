package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTOResp {

	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String banco;
	private String agencia;
	private String conta;
	private Double valorCarteira;

	public static ProdutoraDTOResp paraDTO(Produtora produtora) {
		return new ProdutoraDTOResp(produtora.getId(),
				produtora.getEmail(), produtora.getNomeFantasia(),
				produtora.getRazaoSocial(), produtora.getCnpj(),
				produtora.getBanco(), produtora.getAgencia(),
				produtora.getConta(), produtora.getValorCarteira());
	}
}
