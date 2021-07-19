package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaEventoService {

  private final CategoriaEventoRepository categoriaEventoRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public CategoriaEventoService(
      CategoriaEventoRepository categoriaEventoRepository,
      ValidacaoService validacaoService) {

    this.categoriaEventoRepository = categoriaEventoRepository;
    this.validacaoService = validacaoService;
  }

  public Iterable<CategoriaEvento> obterTodas() {
    return categoriaEventoRepository.findAll();
  }

  public Optional<CategoriaEvento> obterPorId(Long id) {
    return categoriaEventoRepository.findById(id);
  }

  public CategoriaEvento salvar(CategoriaEvento categoriaEvento) {

    if (validacaoService.categoriaJaCadastrada(categoriaEvento.getNome()))
      return null;

    return categoriaEventoRepository.save(categoriaEvento);
  }
}
