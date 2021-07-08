package io.github.heltonricardo.ingressoja.dto.resp;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespGrade {

  private final Long id;
  private final String titulo;
  private final String imagemURL;
  private final Date inicio;
  private final Boolean online;
  private final String url;
  private final String cidade;
  private final String uf;
  private final CategoriaEventoDTORespSimples categoriaEvento;

  public static EventoDTORespGrade paraDTO(Evento evento) {
    return new EventoDTORespGrade(evento.getId(), evento.getTitulo(),
        evento.getImagemURL(), evento.getInicio(), evento.getOnline(),
        evento.getUrl(), evento.getCidade(), evento.getUf(),
        CategoriaEventoDTORespSimples.paraDTO(evento.getCategoriaEvento())
    );
  }
}
