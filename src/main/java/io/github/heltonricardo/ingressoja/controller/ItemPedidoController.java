package io.github.heltonricardo.ingressoja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.repository.ItemPedidoRepository;

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

	@PostMapping
	public void salvarItemPedido(
			@RequestBody @Valid ItemPedido itemPedido) {
		itemPedidoRepository.save(itemPedido);
	}
}
