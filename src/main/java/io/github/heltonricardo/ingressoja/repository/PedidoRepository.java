package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Pedido;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository
    extends PagingAndSortingRepository<Pedido, Long> {
}
