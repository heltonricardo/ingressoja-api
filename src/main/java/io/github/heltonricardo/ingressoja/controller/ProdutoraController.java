package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.ProdutoraDTO;
import io.github.heltonricardo.ingressoja.dto.resp.ProdutoraDTOResp;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.service.ProdutoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("produtora")
public class ProdutoraController {

	private final ProdutoraService produtoraService;

	@Autowired
	public ProdutoraController(ProdutoraService produtoraService) {

		this.produtoraService = produtoraService;
	}

	/******************************* OBTER TODAS ********************************/

	@GetMapping
	public ResponseEntity<List<ProdutoraDTOResp>> obterTodas() {

		List<ProdutoraDTOResp> resp = new ArrayList<>();

		produtoraService.obterTodas()
				.forEach(c -> resp.add(ProdutoraDTOResp.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoraDTOResp> obterPorId(@PathVariable Long id) {

		if (produtoraService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		ProdutoraDTOResp resp = ProdutoraDTOResp
				.paraDTO(produtoraService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<ProdutoraDTOResp> salvar(
			@RequestBody ProdutoraDTO dto) {

		Produtora resp = produtoraService.salvar(dto.paraObjeto());

		return new ResponseEntity<>(ProdutoraDTOResp.paraDTO(resp),
				HttpStatus.CREATED);
	}
}