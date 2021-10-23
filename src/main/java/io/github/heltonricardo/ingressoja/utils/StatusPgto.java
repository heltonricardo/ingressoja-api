package io.github.heltonricardo.ingressoja.utils;

public abstract class StatusPgto {

  public static final String APPROVED = "approved";
  public static final String REJECTED = "rejected";
  public static final String IN_PROGRESS = "in_process";

  public static final String PESQ_APPROVED = converterParaPesquisa(APPROVED);
  public static final String PESQ_REJECTED = converterParaPesquisa(REJECTED);
  public static final String PESQ_IN_PROGRESS =
      converterParaPesquisa(IN_PROGRESS);

  public static String converterParaPesquisa(String termo) {
    return "\"" + termo + "\"";
  }
}
