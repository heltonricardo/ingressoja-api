package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Saque;

public interface SaqueRepository
		extends PagingAndSortingRepository<Saque, Long> {
}
