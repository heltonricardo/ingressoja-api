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

import io.github.heltonricardo.ingressoja.dto.TipoDeIngressoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.TipoDeIngressoDTOResp;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.service.TipoDeIngressoService;

@RestController
@RequestMapping("tipo-de-ingresso")
public class TipoDeIngressoController {

	private final TipoDeIngressoService tipoDeIngressoService;

	@Autowired
	public TipoDeIngressoController(TipoDeIngressoService tipoDeIngressoService) {

		this.tipoDeIngressoService = tipoDeIngressoService;
	}

	/******************************* OBTER TODOS ********************************/

	@GetMapping
	public ResponseEntity<List<TipoDeIngressoDTOResp>> obterTodos() {

		List<TipoDeIngressoDTOResp> resp = new ArrayList<>();

		tipoDeIngressoService.obterTodos()
				.forEach(c -> resp.add(TipoDeIngressoDTOResp.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<TipoDeIngressoDTOResp> obterPorId(
			@PathVariable Long id) {

		TipoDeIngressoDTOResp resp;

		if (tipoDeIngressoService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		resp = TipoDeIngressoDTOResp
				.paraDTO(tipoDeIngressoService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<TipoDeIngressoDTOResp> salvar(
			@RequestBody TipoDeIngressoDTO dto) {

		TipoDeIngresso resp = tipoDeIngressoService.salvar(dto.paraObjeto());

		return new ResponseEntity<>(TipoDeIngressoDTOResp.paraDTO(resp),
				HttpStatus.OK);
	}
}