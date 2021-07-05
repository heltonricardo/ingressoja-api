package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;
  private final ProdutoraService produtoraService;
  private final CategoriaEventoService categoriaEventoService;

  @Autowired
  public EventoService(EventoRepository eventoRepository,
                       ProdutoraService produtoraService,
                       CategoriaEventoService categoriaEvento) {
    this.eventoRepository = eventoRepository;
    this.produtoraService = produtoraService;
    this.categoriaEventoService = categoriaEvento;
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

  public Evento salvar(Long idProdutora, Long idCategoria, Evento evento) {
    Optional<Produtora> pesqProdutora = produtoraService
        .obterPorId(idProdutora);

    Optional<CategoriaEvento> pesqCategoria =
        categoriaEventoService.obterPorId(idCategoria);

    if (pesqProdutora.isEmpty() || pesqCategoria.isEmpty())
      return null;

    Produtora produtora = pesqProdutora.get();
    CategoriaEvento categoriaEvento = pesqCategoria.get();
    evento.setProdutora(produtora);
    evento.setCategoriaEvento(categoriaEvento);

    eventoRepository.save(evento);
    return produtora.getEventos().get(produtora.getEventos().size() - 1);
  }
}
