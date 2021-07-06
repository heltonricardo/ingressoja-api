package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.ProdutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoraService {

	private final ProdutoraRepository produtoraRepository;

	@Autowired
	public ProdutoraService(ProdutoraRepository produtoraRepository) {
		this.produtoraRepository = produtoraRepository;
	}

	public Iterable<Produtora> obterTodas() {
		return produtoraRepository.findAll();
	}

	public Optional<Produtora> obterPorId(Long id) {
		return produtoraRepository.findById(id);
	}

	public Produtora salvar(Produtora produtora) {
		return produtoraRepository.save(produtora);
	}
}