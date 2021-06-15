package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.TipoDeIngresso;

public interface TipoDeIngressoRepository
		extends PagingAndSortingRepository<TipoDeIngresso, Long> {
}
