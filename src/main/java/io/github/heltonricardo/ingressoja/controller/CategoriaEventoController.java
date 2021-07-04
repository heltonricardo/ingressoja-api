package io.github.heltonricardo.ingressoja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.dto.CategoriaEventoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.CategoriaEventoDTORespSimples;
import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.service.CategoriaEventoService;

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
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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
