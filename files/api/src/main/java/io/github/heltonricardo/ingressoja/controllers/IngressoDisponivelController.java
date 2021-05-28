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

import io.github.heltonricardo.ingressoja.model.entities.IngressoDisponivel;
import io.github.heltonricardo.ingressoja.model.repositories.IngressoDisponivelRepository;

@RestController
@RequestMapping("/api/ingresso-disponivel")
public class IngressoDisponivelController {

	@Autowired
	private IngressoDisponivelRepository IngressoDisponivelRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public IngressoDisponivel salvarIngressoDisponivel(
			@Valid IngressoDisponivel IngressoDisponivel) {
		return IngressoDisponivelRepository.save(IngressoDisponivel);
	}

	@GetMapping("/{id}")
	public Optional<IngressoDisponivel> obterIngressoDisponivel(
			@PathVariable int id) {
		return IngressoDisponivelRepository.findById(id);
	}

	@GetMapping
	public Iterable<IngressoDisponivel> obterIngressoDisponivels() {
		return IngressoDisponivelRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<IngressoDisponivel> obterIngressoDisponivelsPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return IngressoDisponivelRepository.findAll(pagina);
	}

	@DeleteMapping("/{id}")
	public void excluirIngressoDisponivel(@PathVariable int id) {
		IngressoDisponivelRepository.deleteById(id);
	}
}
