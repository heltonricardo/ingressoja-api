package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Organizadora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrganizadoraDTORespEvento {

	private Long id;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;

	public static OrganizadoraDTORespEvento paraDTO(Organizadora organizadora) {
		return new OrganizadoraDTORespEvento(organizadora.getId(),
				organizadora.getEmail(), organizadora.getNomeFantasia(),
				organizadora.getRazaoSocial(), organizadora.getCnpj());
	}
}
