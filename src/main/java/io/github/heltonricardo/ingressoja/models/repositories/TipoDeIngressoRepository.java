package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.TipoDeIngresso;

public interface TipoDeIngressoRepository
		extends PagingAndSortingRepository<TipoDeIngresso, Long> {
}
