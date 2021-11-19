package io.github.heltonricardo.ingressoja.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public abstract class Variados {

  /***************************** HOJE ESTÁ ENTRE? *****************************/

  public static boolean hojeEstaEntre(Date d1, Date d2) {

    Date hoje = new Date();

    return d1.before(hoje) && hoje.before(d2);
  }

  public static double round2(double value) {
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
