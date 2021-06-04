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

import io.github.heltonricardo.ingressoja.model.entities.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.repositories.CategoriaEventoRepository;

@RestController
@RequestMapping("categoria-evento")
public class CategoriaEventoController {

	@Autowired
	private CategoriaEventoRepository categoriaEventoRepository;

	@GetMapping("/{id}")
	public Optional<CategoriaEvento> obterCategoriaEventoPorId(
			@PathVariable Long id) {
		return categoriaEventoRepository.findById(id);
	}

	@GetMapping
	public Iterable<CategoriaEvento> obterCategoriaEventos() {
		return categoriaEventoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<CategoriaEvento> obterCategoriaEventosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return categoriaEventoRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarCategoriaEvento(
			@RequestBody @Valid CategoriaEvento categoriaEvento) {
		categoriaEventoRepository.save(categoriaEvento);
	}

	@DeleteMapping("/{id}")
	public Boolean excluirCategoriaEvento(@PathVariable Long id) {
		try {
			categoriaEventoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
