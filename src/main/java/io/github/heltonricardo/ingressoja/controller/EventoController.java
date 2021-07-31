package io.github.heltonricardo.ingressoja.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.heltonricardo.ingressoja.dto_in.EventoDTO;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTOResp;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespGrade;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.service.EventoService;
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
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    resp = EventoDTOResp.paraDTO(eventoService.obterPorId(id).get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<EventoDTOResp> criarEvento(String evento,
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
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

      Evento resp = eventoService.salvar(dto.paraObjeto(), file);

      if (resp == null)
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

      return new ResponseEntity<>(EventoDTOResp.paraDTO(resp),
          HttpStatus.CREATED);

    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
