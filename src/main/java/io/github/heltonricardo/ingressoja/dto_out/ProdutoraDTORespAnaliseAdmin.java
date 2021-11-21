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
public class ProdutoraDTORespAnaliseAdmin {

  private final String razaoSocial;
  private final String cnpj;
  private final Double totalVendas;
  private final Double totalTaxas;

  public static ProdutoraDTORespAnaliseAdmin paraDTO(Produtora produtora) {

    return new ProdutoraDTORespAnaliseAdmin(
        produtora.getRazaoSocial(),
        produtora.getCnpj(),
        produtora.calcularTotalVendido(),
        produtora.calcularTotalTaxa()
    );
  }
}
