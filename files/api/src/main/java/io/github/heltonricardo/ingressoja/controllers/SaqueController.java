package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.Saque;
import io.github.heltonricardo.ingressoja.model.repositories.SaqueRepository;

@RestController
@RequestMapping("saque")
public class SaqueController {

	@Autowired
	private SaqueRepository SaqueRepository;

	@GetMapping("/{id}")
	public Optional<Saque> obterSaquePorId(
			@PathVariable Long id) {
		return SaqueRepository.findById(id);
	}

	@GetMapping
	public Iterable<Saque> obterSaques() {
		return SaqueRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Saque> obterSaquesPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return SaqueRepository.findAll(pagina);
	}

	@PostMapping
	public void salvarSaque(
			@RequestBody @Valid Saque Saque) {
		SaqueRepository.save(Saque);
	}
}
