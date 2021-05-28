package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.CategoriaIngresso;

public interface CategoriaIngressoRepository
		extends PagingAndSortingRepository<CategoriaIngresso, Integer> {
}
