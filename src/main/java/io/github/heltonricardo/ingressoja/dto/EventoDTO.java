package io.github.heltonricardo.ingressoja.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

@Getter
public class EventoDTO {

	private String titulo;
	private String imagemURL;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	private String descricao;
	private Boolean online;
	private String url;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;

	private List<TipoDeIngressoDTO> tiposDeIngresso;

	public Evento paraObjeto() {
		List<TipoDeIngresso> tipos = new ArrayList<>();

		tiposDeIngresso.forEach(t -> tipos.add(t.paraObjeto()));

		return new Evento(titulo, imagemURL, inicio, termino, descricao, online,
				url, logradouro, numero, bairro, cidade, estado, pais, cep, tipos);
	}
}