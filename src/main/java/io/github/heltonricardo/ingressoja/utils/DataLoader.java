package io.github.heltonricardo.ingressoja.utils;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.model.Usuario;
import io.github.heltonricardo.ingressoja.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private final AdministradorService administradorService;

  @Autowired
  public DataLoader(AdministradorService administradorService) {
    this.administradorService = administradorService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    administradorService.salvar(new Administrador("Administrador Padrão",
        new Usuario("a", "a")));
  }
}