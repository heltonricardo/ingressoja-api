package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.EventoDTO;
import io.github.heltonricardo.ingressoja.dto.resp.EventoDTOResp;
import io.github.heltonricardo.ingressoja.dto.resp.EventoDTORespGrade;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
  public ResponseEntity<List<EventoDTORespGrade>> obterTodas() {

    List<EventoDTORespGrade> resp = new ArrayList<>();

    eventoService
        .obterTodos().forEach(c -> resp.add(EventoDTORespGrade.paraDTO(c)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<EventoDTOResp> obterPorId(@PathVariable Long id) {

    EventoDTOResp resp;

    if (eventoService.obterPorId(id).isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    resp = EventoDTOResp.paraDTO(eventoService.obterPorId(id).get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<EventoDTOResp> criarEvento(
      @RequestParam EventoDTO dto,
      @RequestParam MultipartFile file
  ) {

    Evento resp = eventoService.salvar(dto.paraObjeto(), file);

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

    return new ResponseEntity<>(EventoDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }
}
