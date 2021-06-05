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

import io.github.heltonricardo.ingressoja.model.entities.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.model.repositories.TipoDeIngressoRepository;

@RestController
@RequestMapping("tipo-de-ingresso")
public class TipoDeIngressoController {

	@Autowired
	private TipoDeIngressoRepository tipoDeIngressoRepository;

	@GetMapping("/{id}")
	public Optional<TipoDeIngresso> obterTipoDeIngressoPorId(
			@PathVariable Long id) {
		return tipoDeIngressoRepository.findById(id);
	}

	@GetMapping
	public Iterable<TipoDeIngresso> obterTiposDeIngressos() {
		return tipoDeIngressoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<TipoDeIngresso> obterTipoDeIngressosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return tipoDeIngressoRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarTipoDeIngresso(
			@RequestBody @Valid TipoDeIngresso tipoDeIngresso) {
		tipoDeIngressoRepository.save(tipoDeIngresso);
	}

	@DeleteMapping("/{id}")
	public Boolean excluirTipoDeIngresso(@PathVariable Long id) {
		try {
			tipoDeIngressoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}