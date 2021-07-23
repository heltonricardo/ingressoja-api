package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository
    extends PagingAndSortingRepository<ItemPedido, Long> {
}
