package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompradorService {

  private final CompradorRepository compradorRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public CompradorService(CompradorRepository compradorRepository,
                          ValidacaoService validacaoService) {
    this.compradorRepository = compradorRepository;
    this.validacaoService = validacaoService;
  }

  public Iterable<Comprador> obterTodos() {
    return compradorRepository.findAll();
  }

  public Optional<Comprador> obterPorId(Long id) {
    return compradorRepository.findById(id);
  }

  public Optional<Comprador> obterPorEmail(String email) {
    return compradorRepository.findByEmail(email);
  }

  public Optional<Comprador> obterPorCpf(String cpf) {
    return compradorRepository.findByEmail(cpf);
  }

  public Comprador salvar(Comprador comprador) {
    if (validacaoService.emailJaCadastrado(comprador.getEmail()))
      return null;

    if (validacaoService.cpfJaCadastrado(comprador.getCpf()))
      return null;

    return compradorRepository.save(comprador);
  }
}
