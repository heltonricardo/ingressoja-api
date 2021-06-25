package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Administrador;

public interface AdministradorRepository
		extends PagingAndSortingRepository<Administrador, Long> {
	public Iterable<Administrador> findByEmail(String email);
}
