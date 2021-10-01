package io.github.heltonricardo.ingressoja.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.heltonricardo.ingressoja.dto_in.EventoDTO;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTOResp;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespGrade;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.service.EventoService;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import io.github.heltonricardo.ingressoja.validator.EventoOnline;
import io.github.heltonricardo.ingressoja.validator.EventoPresencial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    EventoDTOResp resp = EventoDTOResp.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<EventoDTOResp> salvar(String evento,
                                              MultipartFile file) {
    try {
      if (file == null)
        throw new Exception();

      Gson gson =
          new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm").create();

      EventoDTO dto = gson.fromJson(evento, EventoDTO.class);

      boolean online = dto.getOnline();

      Validator validator =
          Validation.buildDefaultValidatorFactory().getValidator();

      if (validator.validate(dto, online ? EventoOnline.class :
          EventoPresencial.class).size() > 0)
        throw new Exception();

      Evento resp = eventoService.salvar(dto.paraObjeto(), file);

      if (resp == null)
        throw new Exception();

      return new ResponseEntity<>(EventoDTOResp.paraDTO(resp),
          HttpStatus.CREATED);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /******************************** ATUALIZAR *********************************/

  @PutMapping("/{id}")
  public ResponseEntity<EventoDTOResp> atualizar(@PathVariable Long id,
                                                 String evento,
                                                 MultipartFile file) {
    try {
      Gson gson =
          new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm").create();

      EventoDTO dto = gson.fromJson(evento, EventoDTO.class);

      boolean online = dto.getOnline();

      Validator validator =
          Validation.buildDefaultValidatorFactory().getValidator();

      if (validator.validate(dto, online ? EventoOnline.class :
          EventoPresencial.class).size() > 0)
        throw new Exception();

      Evento resp = eventoService.atualizar(dto.paraObjeto(), file, id);

      if (resp == null)
        throw new Exception();

      return new ResponseEntity<>(EventoDTOResp.paraDTO(resp),
          HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  /********************************* INATIVAR *********************************/

  @DeleteMapping("/{id}")
  public ResponseEntity<EventoDTOResp> inativar(@PathVariable Long id) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    eventoService.inativar(pesq.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
