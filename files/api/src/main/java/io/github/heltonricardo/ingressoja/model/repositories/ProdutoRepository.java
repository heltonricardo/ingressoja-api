package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Produto;

public interface ProdutoRepository
		extends PagingAndSortingRepository<Produto, Integer> {

}
