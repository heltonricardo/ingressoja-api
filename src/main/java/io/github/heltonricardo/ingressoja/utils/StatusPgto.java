package io.github.heltonricardo.ingressoja.utils;

import java.util.Arrays;
import java.util.List;

public abstract class StatusPgto {

  public static final String APPROVED = "approved";
  public static final String REJECTED = "rejected";
  public static final String IN_PROGRESS = "in_process";

  private static final List<String> TODOS = Arrays.asList(APPROVED, REJECTED,
      IN_PROGRESS);

  public static final String PESQ_APPROVED = converterParaPesquisa(APPROVED);
  public static final String PESQ_IN_PROGRESS =
      converterParaPesquisa(IN_PROGRESS);

  public static String converterParaPesquisa(String termo) {
    return "\"" + termo + "\"";
  }
}
