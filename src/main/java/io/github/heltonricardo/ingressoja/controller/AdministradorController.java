package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.model.Saque;
import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
