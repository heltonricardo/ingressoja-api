package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.model.ItemPedido;
import io.github.heltonricardo.ingressoja.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
