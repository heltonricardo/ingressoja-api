package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespEdicao {

  private final Long id;
  private final String titulo;
  private final String imagemURL;
  private final Date inicio;
  private final Date termino;
  private final String descricao;
  private final Boolean online;
  private final String url;
  private final String logradouro;
  private final String numero;
  private final String bairro;
  private final String cidade;
  private final String uf;
  private final String cep;
  private final Integer totalIngressos;
  private final Boolean vendaPausada;
  private final ProdutoraDTORespEvento produtora;
  private final CategoriaEventoDTORespSimples categoriaEvento;
  private final List<TipoDeIngressoDTOResp> tiposDeIngresso;

  public static EventoDTORespEdicao paraDTO(Evento evento) {

    List<TipoDeIngressoDTOResp> tipos = evento.getTiposDeIngresso().stream()
        .map(TipoDeIngressoDTOResp::paraDTO).collect(Collectors.toList());

    return new EventoDTORespEdicao(evento.getId(), evento.getTitulo(),
        evento.getImagemURL(), evento.getInicio(), evento.getTermino(),
        evento.getDescricao(), evento.getOnline(), evento.getUrl(),
        evento.getLogradouro(), evento.getNumero(), evento.getBairro(),
        evento.getCidade(), evento.getUf(), evento.getCep(),
        evento.getTotalIngressos(), evento.getVendaPausada(),
        ProdutoraDTORespEvento.paraDTO(evento.getProdutora()),
        CategoriaEventoDTORespSimples.paraDTO(evento.getCategoriaEvento()),
        tipos);
  }
}
