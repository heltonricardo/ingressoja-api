package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaEventoRepository
    extends PagingAndSortingRepository<CategoriaEvento, Long> {

  Optional<CategoriaEvento> findByNome(String nome);
  Optional<CategoriaEvento> findByIdAndAtivoTrue(Long id);
}
