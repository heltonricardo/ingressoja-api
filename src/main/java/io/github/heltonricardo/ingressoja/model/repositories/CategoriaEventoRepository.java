package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.CategoriaEvento;

public interface CategoriaEventoRepository
		extends PagingAndSortingRepository<CategoriaEvento, Long> {
}
