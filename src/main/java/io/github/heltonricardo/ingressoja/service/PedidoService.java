package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Pedido;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.repository.PedidoRepository;
import io.github.heltonricardo.ingressoja.utils.Pagamento;
import io.github.heltonricardo.ingressoja.utils.StatusPedido;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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

  /***************************** ATUALIZAR STATUS *****************************/

  private void atualizarStatus(Pedido pedido) {
    if (pedido.isStatusPgtoPendente()) {
      pedido.atualizaStatusPagamento();
      if (pedido.isStatusPgtoAprovado()) {
        pedido.setStatusPedido(StatusPedido.PROCESSADO);
      } //
      else if (pedido.isStatusPgtoRecusado()) {
        pedido.devolverIngressos();
        pedido.setStatusPedido(StatusPedido.CANC_FALTA_PGTO);
      }
    }
    pedidoRepository.save(pedido);
  }

  /******************************* OBTER TODOS ********************************/

  public Iterable<Pedido> obterTodos() {

    Iterable<Pedido> resp = pedidoRepository.findAll();
    resp.forEach(this::atualizarStatus);
    return resp;
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<Pedido> obterPorId(Long id) {

    Optional<Pedido> resp = pedidoRepository.findById(id);

    if (resp.isPresent()) {
      Pedido pedido = resp.get();
      this.atualizarStatus(pedido);
    }

    return resp;
  }

  /********************************** SALVAR **********************************/

  public String salvar(Pedido pedido) {

    Optional<Evento> pesqEvento =
        eventoService.obterPorId(pedido.getIdEvento(), UsarFiltro.SIM);

    Optional<Comprador> pesqComprador =
        compradorService.obterPorId(pedido.getIdComprador(), UsarFiltro.SIM);

    if (pesqEvento.isEmpty() || pesqComprador.isEmpty())
      return null;

    Evento evento = pesqEvento.get();

    pedido.setEvento(evento);

    boolean erroTipoIngresso = pedido
        .getItensPedido()
        .stream()
        .anyMatch(itemPedido ->
            evento
                .getTiposDeIngresso()
                .stream()
                .noneMatch(tipoDeIngresso ->
                    Objects.equals(tipoDeIngresso.getId(),
                        itemPedido.getIdTipoDeIngresso()))
        );

    if (erroTipoIngresso)
      return null;

    pedido.getItensPedido().forEach(item -> {

      Long pesqId = item.getIdTipoDeIngresso();

      Optional<TipoDeIngresso> pesqTipoDeIngresso =
          tipoDeIngressoService.obterPorId(pesqId);

      if (pesqTipoDeIngresso.isPresent()) {
        TipoDeIngresso tipoDeIngresso = pesqTipoDeIngresso.get();
        tipoDeIngresso.decrementarQntDisp();
        item.setTipoDeIngresso(tipoDeIngresso);
      }
    });

    Comprador comprador = pesqComprador.get();
    pedido.setComprador(comprador);

    pedido.setValorTotal(pedido.calcularTotal());
    pedido.setUrlPagamento("");
    Pedido pedidoSalvo = pedidoRepository.save(pedido);

    String urlPagamento = "";

    if (pedido.getValorTotal() != 0) {

      urlPagamento = Pagamento.gerarUrlPagamento(pedidoSalvo);

      if (urlPagamento == null) {
        pedido.devolverIngressos();
        return null;
      }

      pedido.setUrlPagamento(urlPagamento);
      pedidoRepository.save(pedido);
    }

    return urlPagamento;
  }
}
