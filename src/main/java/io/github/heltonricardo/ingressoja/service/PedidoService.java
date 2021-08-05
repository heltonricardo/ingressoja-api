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

  public Pedido salvar(Pedido pedido) {

    Optional<Evento> pesqEvento =
        eventoService.obterPorId(pedido.getIdEvento());

    Optional<Comprador> pesqComprador =
        compradorService.obterPorId(pedido.getIdComprador());

    if (pesqEvento.isEmpty() || pesqComprador.isEmpty())
      return null;

    Evento evento = pesqEvento.get();

    pedido.setEvento(evento);

    Boolean erroTipoIngresso = pedido
        .getItensPedido()
        .stream()
        .anyMatch(itemPedido ->
            evento
                .getTiposDeIngresso()
                .stream()
                .noneMatch(tipoDeIngresso ->
                    tipoDeIngresso.getId() == itemPedido.getIdTipoDeIngresso())
        );

    if (erroTipoIngresso)
      return null;

    pedido.getItensPedido().forEach(item -> {

      Long pesqId = item.getIdTipoDeIngresso();

      Optional<TipoDeIngresso> pesqTipoDeIngresso =
          tipoDeIngressoService.obterPorId(pesqId);

      TipoDeIngresso tipoDeIngresso = pesqTipoDeIngresso.get();

      tipoDeIngresso.setQuantidadeDisponivel(
          tipoDeIngresso.getQuantidadeDisponivel() - 1);

      item.setTipoDeIngresso(tipoDeIngresso);
    });

    Comprador comprador = pesqComprador.get();
    pedido.setComprador(comprador);

    Double total = pedido.getItensPedido().stream()
        .reduce(0.0, (s, item) -> s + item.getTipoDeIngresso().getValor(),
            Double::sum);

    pedido.setValorTotal(total);

    return pedidoRepository.save(pedido);
  }
}
