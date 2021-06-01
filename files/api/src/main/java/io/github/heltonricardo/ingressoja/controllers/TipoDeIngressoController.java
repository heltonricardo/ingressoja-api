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

import io.github.heltonricardo.ingressoja.model.entities.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.model.repositories.TipoDeIngressoRepository;

@RestController
@RequestMapping("/api/ingresso-disponivel")
public class TipoDeIngressoController {

	@Autowired
	private TipoDeIngressoRepository ingressoDisponivelRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public TipoDeIngresso salvarIngressoDisponivel(
			@Valid TipoDeIngresso IngressoDisponivel) {
		return ingressoDisponivelRepository.save(IngressoDisponivel);
	}

	@GetMapping("/{id}")
	public Optional<TipoDeIngresso> obterIngressoDisponivel(
			@PathVariable Long id) {
		return ingressoDisponivelRepository.findById(id);
	}

	@GetMapping
	public Iterable<TipoDeIngresso> obterIngressoDisponivels() {
		return ingressoDisponivelRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<TipoDeIngresso> obterIngressoDisponivelsPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return ingressoDisponivelRepository.findAll(pagina);
	}

	@DeleteMapping("/{id}")
	public void excluirIngressoDisponivel(@PathVariable Long id) {
		ingressoDisponivelRepository.deleteById(id);
	}
}
