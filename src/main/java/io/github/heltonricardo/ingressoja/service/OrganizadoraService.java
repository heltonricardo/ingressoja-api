package io.github.heltonricardo.ingressoja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.heltonricardo.ingressoja.model.Organizadora;
import io.github.heltonricardo.ingressoja.repository.OrganizadoraRepository;

@Service
public class OrganizadoraService {
	
	private final OrganizadoraRepository organizadoraRepository;
	
	@Autowired
	public OrganizadoraService(OrganizadoraRepository organizadoraRepository) {
		this.organizadoraRepository = organizadoraRepository;
	}
	
	public Organizadora salvar(Organizadora organizadora) {
		return organizadoraRepository.save(organizadora);
	}
}
