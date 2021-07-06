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
  private String uf;
  private String pais;
  private String cep;
  private ProdutoraDTORespEvento produtora;
  private CategoriaEventoDTORespSimples categoriaEvento;
  private List<TipoDeIngressoDTOResp> tiposDeIngresso;

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
