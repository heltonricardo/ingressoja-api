package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.models.entities.Organizadora;
import io.github.heltonricardo.ingressoja.models.entities.Saque;
import io.github.heltonricardo.ingressoja.models.repositories.OrganizadoraRepository;
import io.github.heltonricardo.ingressoja.services.ValidacaoService;

@RestController
@RequestMapping("organizadora")
public class OrganizadoraController {

	@Autowired
	private OrganizadoraRepository organizadoraRepository;
	@Autowired
	private ValidacaoService validacaoService;

	@GetMapping("/{id}")
	public Optional<Organizadora> obterOrganizadoraPorId(@PathVariable Long id) {
		return organizadoraRepository.findById(id);
	}

	@GetMapping
	public Iterable<Organizadora> obterOrganizadorasPorPagina() {
		return organizadoraRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> salvarOrganizadora(
			@RequestBody @Valid Organizadora organizadora) {
		try {
			String cnpj = organizadora.getCnpj();
			String email = organizadora.getEmail();
			if (organizadoraRepository.findByCnpj(cnpj).iterator().hasNext()
					|| validacaoService.emailJaCadastrado(email)) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			// TODO: VALIDAÇÕES
			// return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			organizadoraRepository.save(organizadora);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirOrganizadora(@PathVariable Long id) {
		try {
			Organizadora pesq = obterOrganizadoraPorId(id).get();
			pesq.setAtivo(false);
			organizadoraRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/saque")
	public ResponseEntity<?> novoSaque(@PathVariable Long id,
			@RequestBody @Valid Saque saque) {
		try {
			Organizadora pesq = obterOrganizadoraPorId(id).get();
			if (pesq == null) {
				throw new Exception();
			}
			pesq.addSaque(saque);
			organizadoraRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}