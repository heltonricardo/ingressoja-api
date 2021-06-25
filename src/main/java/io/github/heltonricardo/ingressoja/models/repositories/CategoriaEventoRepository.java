package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.CategoriaEvento;

public interface CategoriaEventoRepository
		extends PagingAndSortingRepository<CategoriaEvento, Long> {
}
