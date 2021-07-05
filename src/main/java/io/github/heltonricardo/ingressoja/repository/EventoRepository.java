package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Evento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository
		extends PagingAndSortingRepository<Evento, Long> {
}
