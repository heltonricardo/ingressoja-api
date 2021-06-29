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

import io.github.heltonricardo.ingressoja.dto.OrganizadoraDTOResp;
import io.github.heltonricardo.ingressoja.dto.OrganizadoraDTO;
import io.github.heltonricardo.ingressoja.model.Organizadora;
import io.github.heltonricardo.ingressoja.service.OrganizadoraService;

@RestController
@RequestMapping("organizadora")
public class OrganizadoraController {

	private final OrganizadoraService organizadoraService;

	@Autowired
	public OrganizadoraController(OrganizadoraService organizadoraService) {

		this.organizadoraService = organizadoraService;
	}

	/******************************* OBTER TODAS ********************************/

	@GetMapping
	public ResponseEntity<List<OrganizadoraDTOResp>> obterTodas() {

		List<OrganizadoraDTOResp> resp = new ArrayList<>();

		organizadoraService.obterTodas()
				.forEach(c -> resp.add(OrganizadoraDTOResp.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<OrganizadoraDTOResp> obterPorId(@PathVariable Long id) {

		OrganizadoraDTOResp resp;

		if (organizadoraService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		resp = OrganizadoraDTOResp
				.paraDTO(organizadoraService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping
	public ResponseEntity<OrganizadoraDTOResp> salvar(
			@RequestBody OrganizadoraDTO dto) {

		Organizadora resp = organizadoraService.salvar(dto.paraObjeto());

		return new ResponseEntity<>(OrganizadoraDTOResp.paraDTO(resp),
				HttpStatus.OK);
	}
}