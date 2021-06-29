package io.github.heltonricardo.ingressoja.dto;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import lombok.Getter;

@Getter
public class CategoriaEventoDTO {

	private String nome;
	
	public CategoriaEvento paraObjeto() {
		return new CategoriaEvento(nome);
	}
}
