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

import io.github.heltonricardo.ingressoja.model.entities.Administrador;
import io.github.heltonricardo.ingressoja.model.repositories.AdministradorRepository;

@RestController
@RequestMapping("administrador")
public class AdministradorController {

	@Autowired
	private AdministradorRepository administradorRepository;

	@GetMapping("/{id}")
	public Optional<Administrador> obterAdministradorPorId(
			@PathVariable Long id) {
		return administradorRepository.findById(id);
	}

	@GetMapping
	public Iterable<Administrador> obterAdministradores() {
		return administradorRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Administrador> obterAdministradoresPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return administradorRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public void salvarAdministrador(
			@RequestBody @Valid Administrador administrador) {
		administradorRepository.save(administrador);
	}

	@DeleteMapping("/{id}")
	public Boolean excluirAdministrador(@PathVariable Long id) {
		try {
			administradorRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
