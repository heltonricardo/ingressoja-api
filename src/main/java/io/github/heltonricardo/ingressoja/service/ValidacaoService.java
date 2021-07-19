package io.github.heltonricardo.ingressoja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoService {

  private final AdministradorService administradorService;
  private final CompradorService compradorService;
  private final ProdutoraService produtoraService;
  private final CategoriaEventoService categoriaEventoService;

  @Autowired
  public ValidacaoService(@Lazy AdministradorService administradorService,
                          @Lazy CompradorService compradorService,
                          @Lazy ProdutoraService produtoraService,
                          @Lazy CategoriaEventoService categoriaEventoService) {
    this.administradorService = administradorService;
    this.compradorService = compradorService;
    this.produtoraService = produtoraService;
    this.categoriaEventoService = categoriaEventoService;
  }

  public Boolean emailJaCadastrado(String email) {
    return administradorService.obterPorEmail(email).isPresent()
        || compradorService.obterPorEmail(email).isPresent()
        || produtoraService.obterPorEmail(email).isPresent();
  }

  public Boolean cpfJaCadastrado(String cpf) {
    return compradorService.obterPorCpf(cpf).isPresent();
  }

  public Boolean cnpjJaCadastrado(String cnpj) {
    return produtoraService.obterPorCnpj(cnpj).isPresent();
  }

  public Boolean categoriaJaCadastrada(String nome) {
    return categoriaEventoService.obterPorNome(nome).isPresent();
  }
}
