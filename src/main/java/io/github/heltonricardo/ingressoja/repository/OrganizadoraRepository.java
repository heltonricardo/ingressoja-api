package io.github.heltonricardo.ingressoja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.heltonricardo.ingressoja.model.Organizadora;

@Repository
public interface OrganizadoraRepository
		extends PagingAndSortingRepository<Organizadora, Long> {
	public Iterable<Organizadora> findByCnpj(String cpf);
	public Iterable<Organizadora> findByEmail(String email);
}
