package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.models.entities.Comprador;
import io.github.heltonricardo.ingressoja.models.repositories.CompradorRepository;
import io.github.heltonricardo.ingressoja.services.ValidacaoService;

@RestController
@RequestMapping("comprador")
public class CompradorController {

	@Autowired
	private CompradorRepository compradorRepository;
	@Autowired
	private ValidacaoService validacaoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> obterCompradorPorId(@PathVariable Long id) {
		Optional<Comprador> pesquisa = compradorRepository.findById(id);
		
		if (pesquisa.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Comprador comprador = pesquisa.get();
		comprador.getUsuario().setSenha("");
		return ResponseEntity.status(HttpStatus.OK).body(comprador);
	}

	@GetMapping
	public Iterable<Comprador> obterCompradores() {
		return compradorRepository.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> salvarComprador(
			@RequestBody @Valid Comprador comprador) {
		try {
			String cpf = comprador.getCpf();
			String email = comprador.getEmail();
			if (compradorRepository.findByCpf(cpf).iterator().hasNext()
					|| validacaoService.emailJaCadastrado(email)) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			// TODO: VALIDAÇÕES
			// return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			compradorRepository.save(comprador);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> excluirComprador(@PathVariable Long id) {
//		try {
//			Comprador pesq = obterCompradorPorId(id).get();
//			pesq.setAtivo(false);
//			compradorRepository.save(pesq);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			System.out.println(e);
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
}
