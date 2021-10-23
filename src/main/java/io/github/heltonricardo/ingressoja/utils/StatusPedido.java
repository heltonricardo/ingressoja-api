package io.github.heltonricardo.ingressoja.utils;

import java.util.Arrays;
import java.util.List;

public abstract class StatusPedido {

  public static final String PROCESSADO = "Processado";
  public static final String AGUARDANDO_PGTO = "Aguardando pagamento";
  public static final String CANC_ARREPEND = "Cancelado por arrependimento";
  public static final String CANC_FALTA_PGTO = "Cancelado por falta de " +
      "pagamento";
}
