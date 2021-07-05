package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Produtora;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoraRepository
		extends PagingAndSortingRepository<Produtora, Long> {
	public Iterable<Produtora> findByCnpj(String cpf);
	public Iterable<Produtora> findByEmail(String email);
}
