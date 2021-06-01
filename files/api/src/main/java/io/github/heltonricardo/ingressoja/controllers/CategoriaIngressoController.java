package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.CategoriaIngresso;
import io.github.heltonricardo.ingressoja.model.repositories.CategoriaIngressoRepository;

@RestController
@RequestMapping("/api/CategoriaIngressos")
public class CategoriaIngressoController {

	@Autowired
	private CategoriaIngressoRepository categoriaIngressoRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public CategoriaIngresso salvarCategoriaIngresso(
			@Valid CategoriaIngresso CategoriaIngresso) {
		return categoriaIngressoRepository.save(CategoriaIngresso);
	}

	@GetMapping("/{id}")
	public Optional<CategoriaIngresso> obterCategoriaIngresso(
			@PathVariable Long id) {
		return categoriaIngressoRepository.findById(id);
	}

	@GetMapping
	public Iterable<CategoriaIngresso> obterCategoriaIngressos() {
		return categoriaIngressoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<CategoriaIngresso> obterCategoriaIngressosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return categoriaIngressoRepository.findAll(pagina);
	}

	@DeleteMapping("/{id}")
	public void excluirCategoriaIngresso(@PathVariable Long id) {
		categoriaIngressoRepository.deleteById(id);
	}
}
