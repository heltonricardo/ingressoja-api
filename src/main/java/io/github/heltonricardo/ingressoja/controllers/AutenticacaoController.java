package io.github.heltonricardo.ingressoja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.github.heltonricardo.ingressoja.models.entities.Administrador;
import io.github.heltonricardo.ingressoja.models.entities.Comprador;
import io.github.heltonricardo.ingressoja.models.entities.Organizadora;
import io.github.heltonricardo.ingressoja.models.repositories.AdministradorRepository;
import io.github.heltonricardo.ingressoja.models.repositories.CompradorRepository;
import io.github.heltonricardo.ingressoja.models.repositories.OrganizadoraRepository;

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
		String email;
		String senha;
		try {
			email = obj.get("email").asText();
			senha = obj.get("senha").asText();
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Iterable<Administrador> user1 = administradorRepository.findByEmail(email);
		if (user1.iterator().hasNext()) {
			Administrador administrador = user1.iterator().next();
			if (administrador.getUsuario().getSenha().equals(senha))
				return ResponseEntity.status(HttpStatus.OK).body(1);
			else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Iterable<Comprador> user2 = compradorRepository.findByEmail(email);
		if (user2.iterator().hasNext()) {
			Comprador comprador = user2.iterator().next();
			if (comprador.getUsuario().getSenha().equals(senha))
				return ResponseEntity.status(HttpStatus.OK).body(2);
			else
				
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		Iterable<Organizadora> user3 = organizadoraRepository.findByEmail(email);
		if (user3.iterator().hasNext()) {
			Organizadora organizadora = user3.iterator().next();
			if (organizadora.getUsuario().getSenha().equals(senha))
				return ResponseEntity.status(HttpStatus.OK).body(3);
			else
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
