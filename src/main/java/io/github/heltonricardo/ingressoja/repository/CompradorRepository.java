package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Comprador;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompradorRepository
		extends PagingAndSortingRepository<Comprador, Long> {
	public Iterable<Comprador> findByCpf(String cpf);
	public Iterable<Comprador> findByEmail(String email);
}
