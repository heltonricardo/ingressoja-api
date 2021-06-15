package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Evento;

public interface EventoRepository
		extends PagingAndSortingRepository<Evento, Long> {
}
