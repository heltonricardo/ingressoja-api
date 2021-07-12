package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompradorService {

  private final CompradorRepository compradorRepository;
  private final ValidacaoEmailService validacaoEmailService;

  @Autowired
  public CompradorService(CompradorRepository compradorRepository,
                          ValidacaoEmailService validacaoEmailService) {
    this.compradorRepository = compradorRepository;
    this.validacaoEmailService = validacaoEmailService;
  }

  public Iterable<Comprador> obterTodos() {
    return compradorRepository.findAll();
  }

  public Optional<Comprador> obterPorId(Long id) {
    return compradorRepository.findById(id);
  }

  public Comprador salvar(Comprador comprador) {
    if (validacaoEmailService.emailJaCadastrado(comprador.getEmail()))
      return null;

    return compradorRepository.save(comprador);
  }
}
