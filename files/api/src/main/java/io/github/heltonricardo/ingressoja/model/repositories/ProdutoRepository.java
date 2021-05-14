package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.CrudRepository;

import io.github.heltonricardo.ingressoja.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
