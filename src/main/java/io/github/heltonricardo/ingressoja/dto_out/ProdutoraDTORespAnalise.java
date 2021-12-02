package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTORespAnalise {

  private final String razaoSocial;
  private final String cnpj;
  private final List<EventoDTORespAnalise> eventos;

  public static ProdutoraDTORespAnalise paraDTO(Produtora produtora) {

    List<EventoDTORespAnalise> eventos = produtora.getEventos().stream()
        .sorted(Comparator.comparing(Evento::getTitulo))
        .map(EventoDTORespAnalise::paraDTO)
        .collect(Collectors.toList());

    return new ProdutoraDTORespAnalise(
        produtora.getRazaoSocial(),
        produtora.getCnpj(),
        eventos
    );
  }
}
