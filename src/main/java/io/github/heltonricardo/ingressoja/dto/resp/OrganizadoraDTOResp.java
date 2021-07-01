package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Organizadora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizadoraDTOResp {

	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String banco;
	private String agencia;
	private String conta;
	private Double valorCarteira;

	private UsuarioDTOResp usuario;

	public static OrganizadoraDTOResp paraDTO(Organizadora organizadora) {
		return new OrganizadoraDTOResp(organizadora.getId(),
				organizadora.getEmail(), organizadora.getNomeFantasia(),
				organizadora.getRazaoSocial(), organizadora.getCnpj(),
				organizadora.getBanco(), organizadora.getAgencia(),
				organizadora.getConta(), organizadora.getValorCarteira(),
				UsuarioDTOResp.paraDTO(organizadora.getUsuario()));
	}
}
