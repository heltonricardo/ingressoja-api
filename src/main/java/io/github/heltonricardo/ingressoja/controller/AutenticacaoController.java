package io.github.heltonricardo.ingressoja.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.model.Organizadora;
import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import io.github.heltonricardo.ingressoja.repository.CompradorRepository;
import io.github.heltonricardo.ingressoja.repository.OrganizadoraRepository;

@RestController
@RequestMapping("autenticacao")
public class AutenticacaoController {
	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private CompradorRepository compradorRepository;

	@Autowired
	private OrganizadoraRepository organizadoraRepository;

	@PostMapping
	public ResponseEntity<?> logar(@RequestBody ObjectNode obj) {
		String email, senha;
		try {
			email = obj.get("email").asText();
			senha = obj.get("senha").asText();
		} //
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Map<String, Long> resposta = new HashMap<>();

		Iterable<Comprador> user1 = compradorRepository.findByEmail(email);
		if (user1.iterator().hasNext()) {
			Comprador comprador = user1.iterator().next();
			if (comprador.getUsuario().getSenha().equals(senha)) {
				resposta.put("id", comprador.getId());
				resposta.put("tipo", 1L);
				return ResponseEntity.status(HttpStatus.OK).body(resposta);
			} else

				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Iterable<Organizadora> user2 = organizadoraRepository.findByEmail(email);
		if (user2.iterator().hasNext()) {
			Organizadora organizadora = user2.iterator().next();
			if (organizadora.getUsuario().getSenha().equals(senha)) {
				resposta.put("id", organizadora.getId());
				resposta.put("tipo", 2L);
				return ResponseEntity.status(HttpStatus.OK).body(resposta);
			} else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Iterable<Administrador> user3 = administradorRepository.findByEmail(email);
		if (user3.iterator().hasNext()) {
			Administrador administrador = user3.iterator().next();
			if (administrador.getUsuario().getSenha().equals(senha)) {
				resposta.put("id", administrador.getId());
				resposta.put("tipo", 3L);
				return ResponseEntity.status(HttpStatus.OK).body(resposta);
			} else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
