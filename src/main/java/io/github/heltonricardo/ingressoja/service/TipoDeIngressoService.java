package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.repository.TipoDeIngressoRepository;

@Service
public class TipoDeIngressoService {

	private final TipoDeIngressoRepository tipoDeIngressoRepository;

	@Autowired
	public TipoDeIngressoService(
			TipoDeIngressoRepository tipoDeIngressoRepository) {
		this.tipoDeIngressoRepository = tipoDeIngressoRepository;
	}

	public Iterable<TipoDeIngresso> obterTodos() {
		return tipoDeIngressoRepository.findAll();
	}

	public Optional<TipoDeIngresso> obterPorId(Long id) {
		return tipoDeIngressoRepository.findById(id);
	}

	public TipoDeIngresso salvar(TipoDeIngresso tipoDeIngresso) {
		return tipoDeIngressoRepository.save(tipoDeIngresso);
	}
}
