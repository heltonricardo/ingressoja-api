package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespDespesa {

  private final Long id;
  private final String titulo;
  private final List<DespesaDTOResp> despesas;

  public static EventoDTORespDespesa paraDTO(Evento evento) {

    List<DespesaDTOResp> despesas =
        evento.getDespesas().stream().map(DespesaDTOResp::paraDTO)
            .collect(Collectors.toList());

    return new EventoDTORespDespesa(
        evento.getId(), evento.getTitulo(), despesas);
  }
}
