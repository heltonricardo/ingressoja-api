package io.github.heltonricardo.ingressoja.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventoDTOResp {

	private Long id;
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
	private List<TipoDeIngressoDTOResp> tiposDeIngresso;

	public static EventoDTOResp paraDTO(Evento evento) {
		List<TipoDeIngressoDTOResp> tipos = new ArrayList<>();
		
		evento.getTiposDeIngresso()
				.forEach(t -> tipos.add(TipoDeIngressoDTOResp.paraDTO(t)));

		return new EventoDTOResp(evento.getId(), evento.getTitulo(),
				evento.getImagemURL(), evento.getInicio(), evento.getTermino(),
				evento.getDescricao(), evento.getOnline(), evento.getUrl(),
				evento.getLogradouro(), evento.getNumero(), evento.getBairro(),
				evento.getCidade(), evento.getEstado(), evento.getPais(),
				evento.getCep(), tipos);
	}
}
