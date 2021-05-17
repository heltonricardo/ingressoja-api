package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.Produto;
import io.github.heltonricardo.ingressoja.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Produto salvarProduto(@Valid Produto produto) {
		return produtoRepository.save(produto);
	}

	@GetMapping("/{id}")
	public Optional<Produto> obterProduto(@PathVariable int id) {
		return produtoRepository.findById(id);
	}

	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Produto> obterProdutosPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return produtoRepository.findAll(pagina);
	}

	@DeleteMapping("/{id}")
	public void excluirProduto(@PathVariable int id) {
		produtoRepository.deleteById(id);
	}
}
