package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.repository.TipoDeIngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoDeIngressoService {

	private final TipoDeIngressoRepository tipoDeIngressoRepository;

	@Autowired
	public TipoDeIngressoService(
			TipoDeIngressoRepository tipoDeIngressoRepository) {
		this.tipoDeIngressoRepository = tipoDeIngressoRepository;
	}

	public TipoDeIngresso salvar(TipoDeIngresso tipoDeIngresso) {
		return tipoDeIngressoRepository.save(tipoDeIngresso);
	}
}
