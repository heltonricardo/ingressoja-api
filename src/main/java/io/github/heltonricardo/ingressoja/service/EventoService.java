package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Organizadora;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;
  private final OrganizadoraService organizadoraService;
  private final CategoriaEventoService categoriaEventoService;

  @Autowired
  public EventoService(EventoRepository eventoRepository,
                       OrganizadoraService organizadoraService,
                       CategoriaEventoService categoriaEvento) {
    this.eventoRepository = eventoRepository;
    this.organizadoraService = organizadoraService;
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

  public Evento salvar(Long idOrganizadora, Long idCategoria, Evento evento) {
    Optional<Organizadora> pesqOrganizadora = organizadoraService
        .obterPorId(idOrganizadora);

    Optional<CategoriaEvento> pesqCategoria =
        categoriaEventoService.obterPorId(idCategoria);

    if (pesqOrganizadora.isEmpty() || pesqCategoria.isEmpty())
      return null;

    Organizadora organizadora = pesqOrganizadora.get();
    CategoriaEvento categoriaEvento = pesqCategoria.get();
    evento.setOrganizadora(organizadora);
    evento.setCategoriaEvento(categoriaEvento);

    eventoRepository.save(evento);
    return organizadora.getEventos().get(organizadora.getEventos().size() - 1);
  }
}
