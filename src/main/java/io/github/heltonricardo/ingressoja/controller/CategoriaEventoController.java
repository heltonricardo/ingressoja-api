package io.github.heltonricardo.ingressoja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;

@RestController
@RequestMapping("categoria-evento")
public class CategoriaEventoController {

	@Autowired
	private CategoriaEventoRepository categoriaEventoRepository;
	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping("/{id}")
	public Optional<CategoriaEvento> obterCategoriaEventoPorId(
			@PathVariable Long id) {
		return categoriaEventoRepository.findById(id);
	}

	@GetMapping
	public Iterable<CategoriaEvento> obterCategoriaEventos() {
		return categoriaEventoRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarCategoriaEvento(
			@RequestBody @Valid CategoriaEvento categoriaEvento) {
		categoriaEventoRepository.save(categoriaEvento);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirCategoriaEvento(@PathVariable Long id) {
		try {
			CategoriaEvento pesq = obterCategoriaEventoPorId(id).get();
			pesq.setAtivo(false);
			categoriaEventoRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}/cadastrar-evento/{idEvento}")
	public ResponseEntity<?> cadastrarEvento(@PathVariable Long id,
			@PathVariable Long idEvento) {
		try {
			CategoriaEvento categoria = obterCategoriaEventoPorId(id).get();
			Evento evento = eventoRepository.findById(idEvento).get();
			if (categoria == null || evento == null) {
				throw new Exception();
			}
			categoria.addEvento(evento);
			categoriaEventoRepository.save(categoria);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
