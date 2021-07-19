package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

@Getter
public class TipoDeIngressoDTO {

	String nome;
	Double valor;
	String descricao;
	Integer quantidadeTotal;

	public TipoDeIngresso paraObjeto() {
		return new TipoDeIngresso(nome, valor, descricao, quantidadeTotal);
	}
}
