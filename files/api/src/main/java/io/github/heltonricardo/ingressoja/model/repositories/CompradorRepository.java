package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Comprador;

public interface CompradorRepository
		extends PagingAndSortingRepository<Comprador, Long> {
}
