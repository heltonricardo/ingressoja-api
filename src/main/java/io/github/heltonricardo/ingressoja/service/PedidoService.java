package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final EventoService eventoService;
  private final CompradorService compradorService;

  @Autowired
  public PedidoService(PedidoRepository pedidoRepository,
                       EventoService eventoService,
                       CompradorService compradorService) {
    this.pedidoRepository = pedidoRepository;
    this.eventoService = eventoService;
    this.compradorService = compradorService;
  }

  /********************************** SALVAR **********************************/

  public Pedido salvar(Long idEvento, Long idComprador, Pedido pedido) {

    Optional<Evento> pesqEvento = eventoService.obterPorId(idEvento);

    Optional<Comprador> pesqComprador =
        compradorService.obterPorId(idComprador);

    if (pesqEvento.isEmpty() || pesqComprador.isEmpty())
      return null;

    Comprador comprador = pesqComprador.get();
    comprador.adicionaPedido(pedido);

    return pedidoRepository.save(pedido);
  }
}
