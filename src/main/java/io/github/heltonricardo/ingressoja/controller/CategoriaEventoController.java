package io.github.heltonricardo.ingressoja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	/******************************* OBTER TODAS ********************************/

	@GetMapping
	public ResponseEntity<List<CategoriaEventoDTOResp>> obterTodas() {
		List<CategoriaEventoDTOResp> resp = new ArrayList<>();
		categoriaEventoService.obterTodas()
				.forEach(c -> resp.add(CategoriaEventoDTOResp.paraDTO(c)));
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaEventoDTOResp> obterPorId(
			@PathVariable Long id) {
		
		CategoriaEventoDTOResp resp;
		
		if (categoriaEventoService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		resp = CategoriaEventoDTOResp
					.paraDTO(categoriaEventoService.obterPorId(id).get());
		
		return new ResponseEntity<>(resp, HttpStatus.OK);
		
	}
	
	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<CategoriaEventoDTOResp> salvar(
			@RequestBody CategoriaEventoDTO dto) {
		CategoriaEvento categoriaEvento = categoriaEventoService
				.salvar(dto.paraObjeto());
		return new ResponseEntity<>(CategoriaEventoDTOResp.paraDTO(categoriaEvento),
				HttpStatus.OK);
	}
}
