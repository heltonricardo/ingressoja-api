package io.github.heltonricardo.ingressoja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.repositories.AdministradorRepository;
import io.github.heltonricardo.ingressoja.model.repositories.CompradorRepository;
import io.github.heltonricardo.ingressoja.model.repositories.OrganizadoraRepository;

@Service
public class ValidacaoService {

	@Autowired
	private AdministradorRepository administradorRepository;
	@Autowired
	private CompradorRepository compradorRepository;
	@Autowired
	private OrganizadoraRepository organizadoraRepository;

	public Boolean emailJaCadastrado(String email) {
		return administradorRepository.findByEmail(email).iterator().hasNext()
				|| compradorRepository.findByEmail(email).iterator().hasNext()
				|| organizadoraRepository.findByEmail(email).iterator().hasNext();
	}
}
