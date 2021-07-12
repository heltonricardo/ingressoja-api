package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

  private final AdministradorRepository administradorRepository;
  private final ValidacaoEmailService validacaoEmailService;

  @Autowired
  public AdministradorService(AdministradorRepository administradorRepository,
                              ValidacaoEmailService validacaoEmailService) {
    this.administradorRepository = administradorRepository;
    this.validacaoEmailService = validacaoEmailService;
  }

  public Iterable<Administrador> obterTodos() {
    return administradorRepository.findAll();
  }

  public Optional<Administrador> obterPorId(Long id) {
    return administradorRepository.findById(id);
  }

  public Administrador salvar(Administrador administrador) {
    if (validacaoEmailService.emailJaCadastrado(administrador.getEmail()))
      return null;

    return administradorRepository.save(administrador);
  }
}
