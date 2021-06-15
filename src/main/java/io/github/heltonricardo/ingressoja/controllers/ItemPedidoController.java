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

import io.github.heltonricardo.ingressoja.model.entities.ItemPedido;
import io.github.heltonricardo.ingressoja.model.repositories.ItemPedidoRepository;

@RestController
@RequestMapping("item-pedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@GetMapping("/{id}")
	public Optional<ItemPedido> obterItemPedidoPorId(
			@PathVariable Long id) {
		return itemPedidoRepository.findById(id);
	}

	@GetMapping
	public Iterable<ItemPedido> obterItemPedidos() {
		return itemPedidoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<ItemPedido> obterItemPedidosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return itemPedidoRepository.findAll(pagina);
	}

	@PostMapping
	public void salvarItemPedido(
			@RequestBody @Valid ItemPedido ItemPedido) {
		itemPedidoRepository.save(ItemPedido);
	}
}
