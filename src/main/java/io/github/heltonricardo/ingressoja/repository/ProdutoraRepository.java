package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Produtora;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoraRepository
    extends PagingAndSortingRepository<Produtora, Long> {
  Optional<Produtora> findByCnpj(String cnpj);
}
