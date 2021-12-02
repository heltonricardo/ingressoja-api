package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.*;
import io.github.heltonricardo.ingressoja.repository.PedidoRepository;
import io.github.heltonricardo.ingressoja.utils.Pagamento;
import io.github.heltonricardo.ingressoja.utils.StatusPedido;
import io.github.heltonricardo.ingressoja.utils.StatusPgto;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final EventoService eventoService;
  private final CompradorService compradorService;
  private final TipoDeIngressoService tipoDeIngressoService;
  private final DespesaService despesaService;

  @Autowired
  public PedidoService(PedidoRepository pedidoRepository,
                       EventoService eventoService,
                       CompradorService compradorService,
                       TipoDeIngressoService tipoDeIngressoService,
                       DespesaService despesaService) {
    this.pedidoRepository = pedidoRepository;
    this.eventoService = eventoService;
    this.compradorService = compradorService;
    this.tipoDeIngressoService = tipoDeIngressoService;
    this.despesaService = despesaService;
  }

  /***************************** ATUALIZAR STATUS *****************************/

  public void atualizarStatus(Pedido pedido) {

    if (!pedido.isStatusPgtoPendente())
      return;

    if (pedido.isPedidoGratis()) {
      pedido.getEvento().diminuirVendasPendentes();
      pedido.getEvento().aumentarVendasProcessadas();
      pedido.setStatusPedido(StatusPedido.PROCESSADO);
      pedido.setStatusPagamento(StatusPgto.NAO_SE_APLICA);
    } //
    else {
      pedido.atualizarStatusPagamento();

      if (pedido.isStatusPgtoAprovado()) {
        pedido.getEvento().diminuirVendasPendentes();
        pedido.getEvento().aumentarVendasProcessadas();
        pedido.setStatusPedido(StatusPedido.PROCESSADO);
      } //
      else if (pedido.isStatusPgtoRecusado()) {
        pedido.devolverIngressos();
        pedido.getEvento().diminuirVendasPendentes();
        pedido.getEvento().aumentarVendasCanceladasPgto();
        pedido.setStatusPedido(StatusPedido.CANC_FALTA_PGTO);
      }
    }
    eventoService.salvarAtualizacao(pedido.getEvento());
    pedidoRepository.save(pedido);
  }

  /******************************* OBTER TODOS ********************************/

  public Page<Pedido> obterTodos(Integer numeroPagina) {

    pedidoRepository.findAll().forEach(this::atualizarStatus);

    Pageable pagina =
        PageRequest.of(Math.max(0, numeroPagina), 10, Sort.by("id"));

    return pedidoRepository.findAllByStatusPedido("Processado", pagina);
  }

  /**************************** OBTER ENTRE DATAS *****************************/

  public Iterable<Pedido> obterEntreDatas(Date inicio, Date termino) {

    pedidoRepository.findAll().forEach(this::atualizarStatus);

    return pedidoRepository.findByDataHoraBetween(inicio, termino);
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

  /********************************* CANCELAR *********************************/

  public boolean cancelar(Pedido pedido) {

    if (!Objects.equals(pedido.getStatusPedido(), StatusPedido.PROCESSADO))
      return false;

    if (pedido.getItensPedido().stream().anyMatch(ItemPedido::getUtilizado))
      return false;

    Calendar somaSeteDias = Calendar.getInstance();
    somaSeteDias.setTime(pedido.getDataHora());
    somaSeteDias.add(Calendar.DAY_OF_MONTH, 7);
    Date prazoMaxCanc = somaSeteDias.getTime();
    Date hoje = new Date();

    if (hoje.compareTo(prazoMaxCanc) > 0)
      return false;

    pedido.devolverIngressos();
    pedido.getEvento().diminuirVendasProcessadas();
    pedido.setStatusPagamento(StatusPgto.REEMBOLSADO);
    pedido.getEvento().aumentarVendasCanceladasSolic();
    pedido.setStatusPedido(StatusPedido.CANC_ARREPEND);

    Despesa despesaFixa = pedido.getEvento().getDespesas().get(0);
    Double taxa = pedido.calcularTaxaPlataforma();
    despesaFixa.subtrairDaDespesa(taxa);
    despesaService.salvar(despesaFixa);

    pedidoRepository.save(pedido);
    eventoService.salvarAtualizacao(pedido.getEvento());
    Pagamento.realizarCancelamento(pedido);
    return true;
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

    boolean erroTipoIngresso = pedido.getItensPedido().stream()
        .anyMatch(itemPedido -> evento.getTiposDeIngresso().stream()
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
        item.setPedido(pedido);
      }
    });

    Comprador comprador = pesqComprador.get();
    pedido.setComprador(comprador);

    pedido.setUrlPagamento("");
    pedido.setValorTotal(pedido.calcularTotal());
    Pedido pedidoSalvo = pedidoRepository.save(pedido);
    String urlPagamento = "";

    if (!pedido.isPedidoGratis()) {
      urlPagamento = Pagamento.gerarUrlPagamento(pedidoSalvo);

      if (urlPagamento == null) {
        pedido.devolverIngressos();
        return null;
      }

      pedido.setUrlPagamento(urlPagamento);
      pedidoRepository.save(pedido);

      Despesa despesaFixa = pedido.getEvento().getDespesas().get(0);
      Double taxa = pedido.calcularTaxaPlataforma();
      despesaFixa.somarNaDespesa(taxa);
      despesaService.salvar(despesaFixa);
    }

    pedido.getEvento().aumentarTotalVendas();
    pedido.getEvento().aumentarVendasPendentes();
    eventoService.salvarAtualizacao(pedido.getEvento());
    return urlPagamento;
  }
}
