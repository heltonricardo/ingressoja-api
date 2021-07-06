package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTOResp {

  private final Long id;
  private final String titulo;
  private final String imagemURL;
  private final LocalDateTime inicio;
  private final LocalDateTime termino;
  private final String descricao;
  private final Boolean online;
  private final String url;
  private final String logradouro;
  private final String numero;
  private final String bairro;
  private final String cidade;
  private final String uf;
  private final String pais;
  private final String cep;
  private final ProdutoraDTORespEvento produtora;
  private final CategoriaEventoDTORespSimples categoriaEvento;
  private final List<TipoDeIngressoDTOResp> tiposDeIngresso;

  public static EventoDTOResp paraDTO(Evento evento) {
    List<TipoDeIngressoDTOResp> tipos = new ArrayList<>();

    evento.getTiposDeIngresso()
        .forEach(t -> tipos.add(TipoDeIngressoDTOResp.paraDTO(t)));

    return new EventoDTOResp(evento.getId(), evento.getTitulo(),
        evento.getImagemURL(), evento.getInicio(), evento.getTermino(),
        evento.getDescricao(), evento.getOnline(), evento.getUrl(),
        evento.getLogradouro(), evento.getNumero(), evento.getBairro(),
        evento.getCidade(), evento.getUf(), evento.getPais(),
        evento.getCep(),
        ProdutoraDTORespEvento.paraDTO(evento.getProdutora()),
        CategoriaEventoDTORespSimples.paraDTO(evento.getCategoriaEvento()),
        tipos);
  }
}
