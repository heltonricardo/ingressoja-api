package io.github.heltonricardo.ingressoja.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.dto.CategoriaEventoDTO;
import io.github.heltonricardo.ingressoja.dto.CategoriaEventoDTOResp;
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

	/******************************* MÉTODOS HTTP *******************************/

	@GetMapping
	public ResponseEntity<Iterable<CategoriaEvento>> obterTodas() {
		return new ResponseEntity<>(categoriaEventoService.obterTodas(),
				HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<CategoriaEvento>> obterPorId(Long id) {
		return new ResponseEntity<>(categoriaEventoService.obterPorId(id),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaEventoDTOResp> salvar(
			@RequestBody CategoriaEventoDTO dto) {
		CategoriaEvento categoriaEvento = categoriaEventoService
				.salvar(dto.paraObjeto());
		return new ResponseEntity<>(CategoriaEventoDTOResp.paraDTO(categoriaEvento),
				HttpStatus.OK);
	}
}
