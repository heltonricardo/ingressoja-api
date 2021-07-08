package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final EventoService eventoService;
  private final CompradorService compradorService;
  private final TipoDeIngressoService tipoDeIngressoService;

  @Autowired
  public PedidoService(PedidoRepository pedidoRepository,
                       EventoService eventoService,
                       CompradorService compradorService,
                       TipoDeIngressoService tipoDeIngressoService) {
    this.pedidoRepository = pedidoRepository;
    this.eventoService = eventoService;
    this.compradorService = compradorService;
    this.tipoDeIngressoService = tipoDeIngressoService;
  }

  /******************************* OBTER TODOS ********************************/

  public Iterable<Pedido> obterTodos() {
    return pedidoRepository.findAll();
  }

  public Optional<Pedido> obterPorId(Long id) {
    return pedidoRepository.findById(id);
  }

  /********************************** SALVAR **********************************/

  public Pedido salvar(Long idEvento, Long idComprador, Pedido pedido) {

    Optional<Evento> pesqEvento = eventoService.obterPorId(idEvento);

    Optional<Comprador> pesqComprador =
        compradorService.obterPorId(idComprador);

    if (pesqEvento.isEmpty() || pesqComprador.isEmpty())
      return null;

    try {
      pedido.getItensPedido().forEach(item -> {

        Long pesqId = item.getTipoDeIngresso().getId();

        Optional<TipoDeIngresso> pesqTipoDeIngresso =
            tipoDeIngressoService.obterPorId(pesqId);

        TipoDeIngresso tipoDeIngresso = pesqTipoDeIngresso.get();

        item.setTipoDeIngresso(tipoDeIngresso);
      });
    } catch (Exception e) {
      return null;
    }

    Double total = pedido.getItensPedido().stream()
        .reduce(0.0, (s, item) -> s + item.getTipoDeIngresso().getValor(),
            Double::sum);

    pedido.setValorTotal(total);

    Comprador comprador = pesqComprador.get();
    comprador.adicionaPedido(pedido);

    return pedidoRepository.save(pedido);
  }
}
