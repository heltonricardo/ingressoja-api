package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.Pedido;
import io.github.heltonricardo.ingressoja.model.repositories.PedidoRepository;

@RestController
@RequestMapping("pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/{id}")
	public Optional<Pedido> obterPedidoPorId(
			@PathVariable Long id) {
		return pedidoRepository.findById(id);
	}

	@GetMapping
	public Iterable<Pedido> obterPedidos() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Pedido> obterPedidosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return pedidoRepository.findAll(pagina);
	}

	@PostMapping
	public void salvarPedido(
			@RequestBody @Valid Pedido Pedido) {
		pedidoRepository.save(Pedido);
	}
}
