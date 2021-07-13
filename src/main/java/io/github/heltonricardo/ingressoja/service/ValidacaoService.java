package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import io.github.heltonricardo.ingressoja.repository.ProdutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoService {

	@Autowired
	private AdministradorRepository administradorRepository;
	@Autowired
	private CompradorRepository compradorRepository;
	@Autowired
	private ProdutoraRepository produtoraRepository;

	public Boolean emailJaCadastrado(String email) {
		return administradorRepository.findByEmail(email).iterator().hasNext()
				|| compradorRepository.findByEmail(email).iterator().hasNext()
				|| produtoraRepository.findByEmail(email).iterator().hasNext();
	}
}
