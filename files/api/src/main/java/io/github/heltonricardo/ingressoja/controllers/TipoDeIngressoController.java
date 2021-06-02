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
@RequestMapping("/api/tipo-de-ingresso")
public class TipoDeIngressoController {

	@Autowired
	private TipoDeIngressoRepository tipoDeIngressoRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public TipoDeIngresso tipoDeIngresso(
			@Valid TipoDeIngresso tipoDeIngresso) {
		return tipoDeIngressoRepository.save(tipoDeIngresso);
	}

	@GetMapping("/{id}")
	public Optional<TipoDeIngresso> obterTipoDeIngresso(
			@PathVariable Long id) {
		return tipoDeIngressoRepository.findById(id);
	}

	@GetMapping
	public Iterable<TipoDeIngresso> obterTiposDeIngresso() {
		return tipoDeIngressoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<TipoDeIngresso> obterTipoDeIngressoPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return tipoDeIngressoRepository.findAll(pagina);
	}

	@DeleteMapping("/{id}")
	public void excluirTipoDeIngresso(@PathVariable Long id) {
		tipoDeIngressoRepository.deleteById(id);
	}
}
