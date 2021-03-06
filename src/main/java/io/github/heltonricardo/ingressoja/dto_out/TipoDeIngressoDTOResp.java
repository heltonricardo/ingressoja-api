package io.github.heltonricardo.ingressoja.dto_out;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoDeIngressoDTOResp {

  private final Long id;
  private final String nome;
  private final Double valor;
  private final String descricao;
  private final Integer quantidadeDisponivel;
  private final Date inicio;
  private final Date termino;

  public static TipoDeIngressoDTOResp paraDTO(TipoDeIngresso tipoDeIngresso) {
    return new TipoDeIngressoDTOResp(
        tipoDeIngresso.getId(),
        tipoDeIngresso.getNome(),
        tipoDeIngresso.getValor(),
        tipoDeIngresso.getDescricao(),
        tipoDeIngresso.getQuantidadeDisponivel(),
        tipoDeIngresso.getInicio(),
        tipoDeIngresso.getTermino());
  }
}
