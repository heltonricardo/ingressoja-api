package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompradorService {

	private final CompradorRepository compradorRepository;

	@Autowired
	public CompradorService(CompradorRepository compradorRepository) {
		this.compradorRepository = compradorRepository;
	}

	public Iterable<Comprador> obterTodos() {
		return compradorRepository.findAll();
	}

	public Optional<Comprador> obterPorId(Long id) {
		return compradorRepository.findById(id);
	}

	public Comprador salvar(Comprador comprador) {
		return compradorRepository.save(comprador);
	}
}
