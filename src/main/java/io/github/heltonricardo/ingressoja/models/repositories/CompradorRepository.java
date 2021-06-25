package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Comprador;

public interface CompradorRepository
		extends PagingAndSortingRepository<Comprador, Long> {
	public Iterable<Comprador> findByCpf(String cpf);
	public Iterable<Comprador> findByEmail(String email);
}
