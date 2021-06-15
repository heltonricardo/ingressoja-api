package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Pedido;

public interface PedidoRepository
		extends PagingAndSortingRepository<Pedido, Long> {
}
