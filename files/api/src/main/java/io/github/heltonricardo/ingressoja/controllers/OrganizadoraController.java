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

import io.github.heltonricardo.ingressoja.model.entities.Organizadora;
import io.github.heltonricardo.ingressoja.model.repositories.OrganizadoraRepository;

@RestController
@RequestMapping("organizadora")
public class OrganizadoraController {

	@Autowired
	private OrganizadoraRepository organizadoraRepository;

	@GetMapping("/{id}")
	public Optional<Organizadora> obterOrganizadoraPorId(
			@PathVariable Long id) {
		return organizadoraRepository.findById(id);
	}

	@GetMapping
	public Iterable<Organizadora> obterOrganizadora() {
		return organizadoraRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Organizadora> obterOrganizadorasPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return organizadoraRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarOrganizadora(
			@RequestBody @Valid Organizadora organizadora) {
		organizadoraRepository.save(organizadora);
	}

	@DeleteMapping("/{id}")
	public Boolean excluirOrganizadora(@PathVariable Long id) {
		try {
			organizadoraRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
