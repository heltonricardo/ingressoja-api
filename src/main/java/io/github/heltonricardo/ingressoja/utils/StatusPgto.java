package io.github.heltonricardo.ingressoja.utils;

import java.util.Arrays;
import java.util.List;

public abstract class StatusPgto {

  public static final String APROVADO = "approved";
  public static final String RECUSADO = "rejected";
  public static final String PENDENTE = "in_process";

  private static final List<String> TODOS = Arrays.asList(APROVADO, RECUSADO,
      PENDENTE);

  public static final String PESQ_APPROVED = converterParaPesquisa(APROVADO);
  public static final String PESQ_RECUSADO = converterParaPesquisa(RECUSADO);
  public static final String PESQ_IN_PROGRESS =
      converterParaPesquisa(PENDENTE);

  public static String converterParaPesquisa(String termo) {
    return "\"" + termo + "\"";
  }
}
