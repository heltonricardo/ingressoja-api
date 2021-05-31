package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Administrador;

public interface AdministradorRepository
		extends PagingAndSortingRepository<Administrador, Long> {
}
