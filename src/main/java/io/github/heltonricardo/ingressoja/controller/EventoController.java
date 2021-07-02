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

import io.github.heltonricardo.ingressoja.dto.EventoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.EventoDTOResp;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.service.EventoService;

@RestController
@RequestMapping("evento")
public class EventoController {

	public final EventoService eventoService;

	@Autowired
	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	/******************************* OBTER TODOS ********************************/

	@GetMapping
	public ResponseEntity<List<EventoDTOResp>> obterTodas() {

		List<EventoDTOResp> resp = new ArrayList<>();

		eventoService.obterTodos().forEach(c -> resp.add(EventoDTOResp.paraDTO(c)));

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/******************************* OBTER POR ID *******************************/

	@GetMapping("/{id}")
	public ResponseEntity<EventoDTOResp> obterPorId(@PathVariable Long id) {

		EventoDTOResp resp;

		if (eventoService.obterPorId(id).isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		resp = EventoDTOResp.paraDTO(eventoService.obterPorId(id).get());

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	/********************************** SALVAR **********************************/

	@PostMapping("/{idOrganizadora}")
	public ResponseEntity<EventoDTOResp> criarEvento(
			@PathVariable Long idOrganizadora, @RequestBody EventoDTO dto) {

		Evento resp = eventoService.salvar(idOrganizadora, dto.paraObjeto());

		if (resp == null)
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		return new ResponseEntity<>(EventoDTOResp.paraDTO(resp),
				HttpStatus.CREATED);
	}
}