package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Pedido;

public interface PedidoRepository
		extends PagingAndSortingRepository<Pedido, Long> {
}
