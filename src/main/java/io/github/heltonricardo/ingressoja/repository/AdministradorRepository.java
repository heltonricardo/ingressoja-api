package io.github.heltonricardo.ingressoja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.heltonricardo.ingressoja.model.Administrador;

@Repository
public interface AdministradorRepository
		extends PagingAndSortingRepository<Administrador, Long> {
	public Iterable<Administrador> findByEmail(String email);
}
