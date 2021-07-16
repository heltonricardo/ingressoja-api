package io.github.heltonricardo.ingressoja.utils;

import java.util.Arrays;

public class Formatador {

  public static String nomeArquivo(Long nome, String arquivo) {
    return nome.toString() + arquivo.substring(arquivo.lastIndexOf("."));
  }

  public static boolean isImagem(String arquivo) {
    return Arrays.asList("bmp", "jpeg", "jpg", "png")
        .stream()
        .anyMatch(i ->
            i.equals(arquivo.substring(arquivo.lastIndexOf(".") + 1))
        );
  }
}
