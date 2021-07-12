package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;
  private final ProdutoraService produtoraService;
  private final CategoriaEventoService categoriaEventoService;
  private final TipoDeIngressoService tipoDeIngressoService;

  @Autowired
  public EventoService(EventoRepository eventoRepository,
                       ProdutoraService produtoraService,
                       CategoriaEventoService categoriaEvento,
                       TipoDeIngressoService tipoDeIngressoService) {
    this.eventoRepository = eventoRepository;
    this.produtoraService = produtoraService;
    this.categoriaEventoService = categoriaEvento;
    this.tipoDeIngressoService = tipoDeIngressoService;
  }

  /******************************* OBTER TODOS ********************************/

  public Iterable<Evento> obterTodos() {
    return eventoRepository.findAll();
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<Evento> obterPorId(Long id) {
    return eventoRepository.findById(id);
  }

  /********************************** SALVAR **********************************/

  public Evento salvar(Evento evento) {
    Optional<Produtora> pesqProdutora = produtoraService
        .obterPorId(evento.getIdProdutora());

    Optional<CategoriaEvento> pesqCategoria =
        categoriaEventoService.obterPorId(evento.getIdCategoria());

    if (pesqProdutora.isEmpty() || pesqCategoria.isEmpty())
      return null;

    Produtora produtora = pesqProdutora.get();
    CategoriaEvento categoriaEvento = pesqCategoria.get();
    evento.setProdutora(produtora);
    evento.setCategoriaEvento(categoriaEvento);
    evento.getTiposDeIngresso().forEach(t -> t.setEvento(evento));

    eventoRepository.save(evento);
    return produtora.getEventos().get(produtora.getEventos().size() - 1);
  }
}
