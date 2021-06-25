package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.models.entities.Evento;
import io.github.heltonricardo.ingressoja.models.repositories.EventoRepository;

@RestController
@RequestMapping("evento")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping("/{id}")
	public Optional<Evento> obterEventoPorId(
			@PathVariable Long id) {
		return eventoRepository.findById(id);
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Evento> obterEventosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return eventoRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarEvento(
			@RequestBody @Valid Evento evento) {
		eventoRepository.save(evento);
	}

	// TODO Retornar codigo http 
	@DeleteMapping("/{id}")
	public Boolean excluirEvento(@PathVariable Long id) {
		try {
			eventoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
