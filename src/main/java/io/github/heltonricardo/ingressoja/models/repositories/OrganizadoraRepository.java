package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Organizadora;

public interface OrganizadoraRepository
		extends PagingAndSortingRepository<Organizadora, Long> {
	public Iterable<Organizadora> findByCnpj(String cpf);
	public Iterable<Organizadora> findByEmail(String email);
}
