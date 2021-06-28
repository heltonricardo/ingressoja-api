package io.github.heltonricardo.ingressoja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.heltonricardo.ingressoja.model.ItemPedido;

@Repository
public interface ItemPedidoRepository
		extends PagingAndSortingRepository<ItemPedido, Long> {
}
