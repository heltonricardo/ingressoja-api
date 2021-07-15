package io.github.heltonricardo.ingressoja.utils;

public class Formatador {

  public static String nomeArquivo(Long nome, String arquivo) {
    return nome.toString() + arquivo.substring(arquivo.lastIndexOf("."));
  }
}
