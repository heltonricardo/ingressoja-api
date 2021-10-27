package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespProdutora {

  private final Long id;
  private final String titulo;
  private final Date inicio;
  private final Boolean online;
  private final Boolean vendaPausada;
  private final CategoriaEventoDTORespSimples categoriaEvento;

  public static EventoDTORespProdutora paraDTO(Evento evento) {
    return new EventoDTORespProdutora(
        evento.getId(), evento.getTitulo(), evento.getInicio(),
        evento.getOnline(), evento.getVendaPausada(),
        CategoriaEventoDTORespSimples.paraDTO(evento.getCategoriaEvento())
    );
  }
}
