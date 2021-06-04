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

import io.github.heltonricardo.ingressoja.model.entities.Comprador;
import io.github.heltonricardo.ingressoja.model.repositories.CompradorRepository;

@RestController
@RequestMapping("comprador")
public class CompradorController {

	@Autowired
	private CompradorRepository compradorRepository;

	@GetMapping("/{id}")
	public Optional<Comprador> obterCompradorPorId(
			@PathVariable Long id) {
		return compradorRepository.findById(id);
	}

	@GetMapping
	public Iterable<Comprador> obterCompradores() {
		return compradorRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Comprador> obterCompradoresPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return compradorRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarComprador(
			@RequestBody @Valid Comprador comprador) {
		compradorRepository.save(comprador);
	}

	@DeleteMapping("/{id}")
	public Boolean excluirComprador(@PathVariable Long id) {
		try {
			compradorRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
