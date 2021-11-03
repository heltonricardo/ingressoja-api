package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Evento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventoRepository
    extends PagingAndSortingRepository<Evento, Long> {

  Optional<Evento> findByIdAndAtivoTrue(Long id);

  Iterable<Evento> findByAtivoTrueAndTerminoGreaterThanEqualOrderByInicioAsc(
      Date date);
}
