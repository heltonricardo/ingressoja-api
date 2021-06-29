package io.github.heltonricardo.ingressoja.service;

import java.util.Optional;

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
	
	public Iterable<Organizadora> obterTodas() {
		return organizadoraRepository.findAll();
	}
	
	public Optional<Organizadora> obterPorId(Long id) {
		return organizadoraRepository.findById(id);
	}
	
	public Organizadora salvar(Organizadora organizadora) {
		return organizadoraRepository.save(organizadora);
	}
}
