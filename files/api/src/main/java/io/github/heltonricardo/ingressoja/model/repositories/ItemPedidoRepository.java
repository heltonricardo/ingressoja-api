package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.ItemPedido;

public interface ItemPedidoRepository
		extends PagingAndSortingRepository<ItemPedido, Long> {
}
