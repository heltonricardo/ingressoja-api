package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Organizadora;

public interface OrganizadoraRepository
		extends PagingAndSortingRepository<Organizadora, Long> {
	public Iterable<Organizadora> findByCnpj(String cpf);
	public Iterable<Organizadora> findByEmail(String email);
}
