package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;

@Service
public class EventoService {

	private final EventoRepository eventoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository) {
		this.eventoRepository = eventoRepository;
	}

	public Iterable<Evento> obterTodos() {
		return eventoRepository.findAll();
	}

	public Optional<Evento> obterPorId(Long id) {
		return eventoRepository.findById(id);
	}

	public Evento salvar(Evento categoriaEvento) {
		return eventoRepository.save(categoriaEvento);
	}
}
