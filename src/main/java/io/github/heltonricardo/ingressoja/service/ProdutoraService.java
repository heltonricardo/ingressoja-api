package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.ProdutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoraService {

  private final ProdutoraRepository produtoraRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public ProdutoraService(ProdutoraRepository produtoraRepository,
                          ValidacaoService validacaoService) {
    this.produtoraRepository = produtoraRepository;
    this.validacaoService = validacaoService;
  }

  public Iterable<Produtora> obterTodas() {
    return produtoraRepository.findAll();
  }

  public Optional<Produtora> obterPorId(Long id) {
    return produtoraRepository.findById(id);
  }

  public Optional<Produtora> obterPorCnpj(String cnpj) {
    return produtoraRepository.findByCnpj(cnpj);
  }

  public Produtora salvar(Produtora produtora) {
    if (validacaoService.emailJaCadastrado(produtora.getUsuario().getEmail()))
      return null;

    if (validacaoService.cnpjJaCadastrado((produtora.getCnpj())))
      return null;

    return produtoraRepository.save(produtora);
  }
}
