package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import io.github.heltonricardo.ingressoja.repository.ProdutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoService {

  @Autowired
  private AdministradorRepository administradorRepository;
  @Autowired
  private CompradorRepository compradorRepository;
  @Autowired
  private ProdutoraRepository produtoraRepository;
  @Autowired
  private CategoriaEventoRepository categoriaEventoRepository;

  public Boolean emailJaCadastrado(String email) {
    return administradorRepository.findByEmail(email).iterator().hasNext()
        || compradorRepository.findByEmail(email).iterator().hasNext()
        || produtoraRepository.findByEmail(email).iterator().hasNext();
  }

  public Boolean cpfJaCadastrado(String cpf) {
    return compradorRepository.findByCpf(cpf).iterator().hasNext();
  }

  public Boolean cnpjJaCadastrado(String cnpj) {
    return produtoraRepository.findByCnpj(cnpj).iterator().hasNext();
  }

  public Boolean categoriaJaCadastrada(String nome) {
    return categoriaEventoRepository.findByNome(nome).iterator().hasNext();
  }
}
