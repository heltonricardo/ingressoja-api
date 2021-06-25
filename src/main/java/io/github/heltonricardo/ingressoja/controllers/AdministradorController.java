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

import io.github.heltonricardo.ingressoja.models.entities.Administrador;
import io.github.heltonricardo.ingressoja.models.entities.Saque;
import io.github.heltonricardo.ingressoja.models.repositories.AdministradorRepository;

@RestController
@RequestMapping("administrador")
public class AdministradorController {

	@Autowired
	private AdministradorRepository administradorRepository;
	
	@GetMapping
	public Iterable<Administrador> obterAdministradores() {
		return administradorRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Administrador> obterAdministradorPorId(
			@PathVariable Long id) {
		return administradorRepository.findById(id);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> editarAdministrador(
			@RequestBody @Valid Administrador administrador) {
		try {
			administradorRepository.save(administrador);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirAdministrador(@PathVariable Long id) {
		try {
			Administrador pesq = obterAdministradorPorId(id).get();
			pesq.setAtivo(false);
			administradorRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{id}/saque")
	public ResponseEntity<?> novoSaque(@PathVariable Long id,
			@RequestBody @Valid Saque saque) {
		try {
			Administrador pesq = obterAdministradorPorId(id).get();
			if (pesq == null) {
				throw new Exception();
			}
			pesq.addSaque(saque);
			administradorRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
