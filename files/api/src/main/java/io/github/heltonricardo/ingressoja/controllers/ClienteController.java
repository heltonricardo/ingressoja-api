package io.github.heltonricardo.ingressoja.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.models.Cliente;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/qualquer")
	public Cliente obterCliente() {
		return new Cliente(13, "Helton", "123.456.789-45");
	}

	// localhost:8080/clientes/1
	@GetMapping("/{id}")
	public Cliente obterClientePorId(@PathVariable int id) {
		return new Cliente(id, "Maria", "987.654.321-00");
	}

	// localhost:8080/clientes?id=2
	@GetMapping
	public Cliente obterClientePorId2(
			@RequestParam(name = "id", defaultValue = "0") int id) {
		return new Cliente(id, "João Silva", "987.654.321-00");
	}
}
