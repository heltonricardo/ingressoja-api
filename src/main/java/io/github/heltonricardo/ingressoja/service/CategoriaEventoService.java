package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;

public class CategoriaEventoService {

	private final CategoriaEventoRepository categoriaEventoRepository;

	@Autowired
	public CategoriaEventoService(
			CategoriaEventoRepository categoriaEventoRepository) {
		this.categoriaEventoRepository = categoriaEventoRepository;
	}
	
	public Iterable<CategoriaEvento> obterTodas() {
		return categoriaEventoRepository.findAll();
	}
	
	public Optional<CategoriaEvento> obterPorId(Long id) {
		return categoriaEventoRepository.findById(id);
	}
	
	public CategoriaEvento salvar(CategoriaEvento categoriaEvento) {
		return categoriaEventoRepository.save(categoriaEvento);
	}
}
