package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEventoRepository
    extends PagingAndSortingRepository<CategoriaEvento, Long> {

  Iterable<CategoriaEvento> findByNome(String nome);
}
