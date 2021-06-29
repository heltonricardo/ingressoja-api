package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
