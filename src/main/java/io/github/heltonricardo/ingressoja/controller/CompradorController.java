package io.github.heltonricardo.ingressoja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.dto.CompradorDTO;
import io.github.heltonricardo.ingressoja.dto.resp.CompradorDTOResp;
import io.github.heltonricardo.ingressoja.model.Comprador;
import io.github.heltonricardo.ingressoja.service.CompradorService;

@RestController
@RequestMapping("comprador")
public class CompradorController {

	private final CompradorService compradorService;

	@Autowired
	public CompradorController(CompradorService compradorService) {

		this.compradorService = compradorService;
	}

	/******************************* OBTER TODOS ********************************/

	@GetMapping
	public ResponseEntity<List<CompradorDTOResp>> obterTodos() {

		List<CompradorDTOResp> resp = new ArrayList<>();

		compradorService.obterTodos()
				.forEach(c -> resp.add(CompradorDTOResp.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<CompradorDTOResp> obterPorId(@PathVariable Long id) {

		if (compradorService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		CompradorDTOResp resp = CompradorDTOResp
				.paraDTO(compradorService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<CompradorDTOResp> salvar(
			@RequestBody CompradorDTO dto) {

		Comprador resp = compradorService.salvar(dto.paraObjeto());

		return new ResponseEntity<>(CompradorDTOResp.paraDTO(resp),
				HttpStatus.OK);
	}
}
