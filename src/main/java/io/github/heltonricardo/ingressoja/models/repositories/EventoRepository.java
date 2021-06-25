package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Evento;

public interface EventoRepository
		extends PagingAndSortingRepository<Evento, Long> {
}
