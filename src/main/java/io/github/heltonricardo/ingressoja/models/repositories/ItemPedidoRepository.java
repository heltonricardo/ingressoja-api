package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.ItemPedido;

public interface ItemPedidoRepository
		extends PagingAndSortingRepository<ItemPedido, Long> {
}
