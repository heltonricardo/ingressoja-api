package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoDeIngressoDTOResp {

	private Long id;
	private String nome;
	private Double valor;
	private String descricao;
	private Integer quantidade;

	public static TipoDeIngressoDTOResp paraDTO(TipoDeIngresso tipoDeIngresso) {
		return new TipoDeIngressoDTOResp(tipoDeIngresso.getId(),
				tipoDeIngresso.getNome(), tipoDeIngresso.getValor(),
				tipoDeIngresso.getDescricao(), tipoDeIngresso.getQuantidade());
	}
}
