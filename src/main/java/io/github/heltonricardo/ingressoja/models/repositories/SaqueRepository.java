package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Saque;

public interface SaqueRepository
		extends PagingAndSortingRepository<Saque, Long> {
}
