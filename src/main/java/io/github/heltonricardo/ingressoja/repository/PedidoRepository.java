package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository
    extends PagingAndSortingRepository<Pedido, Long> {

  Page<Pedido> findAllByStatusPedido(String status, Pageable pageable);
}
