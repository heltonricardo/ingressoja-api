package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.CategoriaEventoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.CategoriaEventoDTORespSimples;
import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.service.CategoriaEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria-evento")
public class CategoriaEventoController {

	private final CategoriaEventoService categoriaEventoService;

	@Autowired
	public CategoriaEventoController(
			CategoriaEventoService categoriaEventoService) {

		this.categoriaEventoService = categoriaEventoService;
	}

	/******************************* OBTER TODAS ********************************/

	@GetMapping
	public ResponseEntity<List<CategoriaEventoDTORespSimples>> obterTodas() {

		List<CategoriaEventoDTORespSimples> resp = new ArrayList<>();

		categoriaEventoService.obterTodas()
				.forEach(c -> resp.add(CategoriaEventoDTORespSimples.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaEventoDTORespSimples> obterPorId(
			@PathVariable Long id) {

		CategoriaEventoDTORespSimples resp;

		if (categoriaEventoService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		resp = CategoriaEventoDTORespSimples
				.paraDTO(categoriaEventoService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<CategoriaEventoDTORespSimples> salvar(
			@RequestBody CategoriaEventoDTO dto) {

		CategoriaEvento resp = categoriaEventoService.salvar(dto.paraObjeto());

		return new ResponseEntity<>(CategoriaEventoDTORespSimples.paraDTO(resp),
				HttpStatus.CREATED);
	}
}
