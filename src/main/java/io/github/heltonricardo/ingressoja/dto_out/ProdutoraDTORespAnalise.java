package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Produtora;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProdutoraDTORespAnalise {

  private final String razaoSocial;
  private final String cnpj;
  private final List<EventoDTORespAnalise> eventos;

  public static ProdutoraDTORespAnalise paraDTO(Produtora produtora) {

    List<EventoDTORespAnalise> eventos = new ArrayList<>();
    produtora.getEventos()
        .stream().map(EventoDTORespAnalise::paraDTO).forEach(eventos::add);

    return new ProdutoraDTORespAnalise(
        produtora.getRazaoSocial(),
        produtora.getCnpj(),
        eventos
    );
  }
}
