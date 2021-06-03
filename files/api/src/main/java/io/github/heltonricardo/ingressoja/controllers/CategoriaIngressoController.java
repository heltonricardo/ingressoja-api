package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.CategoriaIngresso;
import io.github.heltonricardo.ingressoja.model.repositories.CategoriaIngressoRepository;

@RestController
@RequestMapping("categoria-ingresso")
public class CategoriaIngressoController {

	@Autowired
	private CategoriaIngressoRepository categoriaIngressoRepository;

	@GetMapping("/{id}")
	public Optional<CategoriaIngresso> obterCategoriaIngresso(
			@PathVariable Long id) {
		return categoriaIngressoRepository.findById(id);
	}

	@GetMapping
	public Iterable<CategoriaIngresso> obterCategoriaIngressos() {
		return categoriaIngressoRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Boolean salvarCategoriaIngresso(
			@RequestBody @Valid CategoriaIngresso categoriaIngresso) {
		try {			
			categoriaIngressoRepository.save(categoriaIngresso);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@DeleteMapping("/{id}")
	public Boolean excluirCategoriaIngresso(@PathVariable Long id) {
		try {			
			categoriaIngressoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
