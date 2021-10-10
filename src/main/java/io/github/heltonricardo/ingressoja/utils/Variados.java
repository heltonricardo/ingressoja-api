package io.github.heltonricardo.ingressoja.utils;

import java.util.Date;

public abstract class Variados {

  /***************************** HOJE ESTÁ ENTRE? *****************************/

  public static boolean hojeEstaEntre(Date d1, Date d2) {

    Date hoje = new Date();

    return d1.before(hoje) && hoje.before(d2);
  }
}
