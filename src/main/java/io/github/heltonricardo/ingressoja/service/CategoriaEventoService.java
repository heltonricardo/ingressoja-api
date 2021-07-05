package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaEventoService {

	private final CategoriaEventoRepository categoriaEventoRepository;

	@Autowired
	public CategoriaEventoService(
			CategoriaEventoRepository categoriaEventoRepository) {
		this.categoriaEventoRepository = categoriaEventoRepository;
	}
	
	public boolean existeId(Long id) {
		return !categoriaEventoRepository.findById(id).isEmpty();
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
