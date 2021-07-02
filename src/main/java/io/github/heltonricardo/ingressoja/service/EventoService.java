package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Organizadora;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;

@Service
public class EventoService {

	private final EventoRepository eventoRepository;
	private final OrganizadoraService organizadoraService;

	@Autowired
	public EventoService(EventoRepository eventoRepository,
			OrganizadoraService organizadoraService) {
		this.eventoRepository = eventoRepository;
		this.organizadoraService = organizadoraService;
	}

	public Iterable<Evento> obterTodos() {
		return eventoRepository.findAll();
	}

	public Optional<Evento> obterPorId(Long id) {
		return eventoRepository.findById(id);
	}

	public Evento salvar(Long idOrganizadora, Evento evento) {
		Optional<Organizadora> pesq = organizadoraService
				.obterPorId(idOrganizadora);

		if (pesq.isEmpty())
			return null;

		Organizadora organizadora = pesq.get();
		evento.setOrganizadora(organizadora);
		organizadora.adicionaEvento(evento);
		organizadoraService.salvar(organizadora);
		return organizadora.getEventos().get(organizadora.getEventos().size() - 1);
	}
}
