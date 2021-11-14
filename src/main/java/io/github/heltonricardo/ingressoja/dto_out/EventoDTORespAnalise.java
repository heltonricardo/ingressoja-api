package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.Evento;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EventoDTORespAnalise {

  private final String titulo;
  private final Boolean online;
  private final Integer totalIngressos;
  private final Integer qntIngressosVendidos;
  private final Double porcentagemIngressosVendidos;
  private final Double receitaLiquida;
  private final Double receitaBruta;
  private final Double totalDespesas;
  private final List<TipoDeIngressoDTORespAnalise> tiposDeIngresso;
  private final List<DespesaDTORespAnalise> despesas;

  public static EventoDTORespAnalise paraDTO(Evento evento) {

    List<TipoDeIngressoDTORespAnalise> tiposDeIngresso =
        evento.getTiposDeIngresso().stream()
            .map(TipoDeIngressoDTORespAnalise::paraDTO)
            .collect(Collectors.toList());

    List<DespesaDTORespAnalise> despesas =
        evento.getDespesas().stream().map(DespesaDTORespAnalise::paraDTO)
            .collect(Collectors.toList());

    return new EventoDTORespAnalise(
        evento.getTitulo(),
        evento.getOnline(),
        evento.getTotalIngressos(),
        evento.calcularQntIngressosVendidos(),
        evento.calcularPorcentagemIngressosVendidos(),
        evento.calcularReceitaLiquida(),
        evento.calcularReceitaBruta(),
        evento.calcularTotalDespesas(),
        tiposDeIngresso, despesas
    );
  }
}
