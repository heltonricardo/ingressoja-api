package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Comprador;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompradorRepository
    extends PagingAndSortingRepository<Comprador, Long> {

  Optional<Comprador> findByIdAndAtivoTrue(Long id);
  Optional<Comprador> findByCpf(String cpf);
}
