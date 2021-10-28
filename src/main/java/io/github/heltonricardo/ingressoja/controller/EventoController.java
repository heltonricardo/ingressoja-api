package io.github.heltonricardo.ingressoja.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.heltonricardo.ingressoja.dto_in.DespesaDTO;
import io.github.heltonricardo.ingressoja.dto_in.EventoDTO;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTOResp;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespDespesa;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespGrade;
import io.github.heltonricardo.ingressoja.dto_out.EventoDTORespVendas;
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

import javax.validation.Valid;
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
  public ResponseEntity<List<EventoDTORespGrade>> obterTodos() {

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

    Evento evento = pesq.get();

    evento.setIngressosValidos();

    EventoDTOResp resp = EventoDTOResp.paraDTO(evento);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /************************* OBTER POR ID PARA EDIÇÃO *************************/

  @GetMapping("paraEdicao/{id}")
  public ResponseEntity<EventoDTOResp> obterPorIdEdicao(@PathVariable Long id) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    EventoDTOResp resp = EventoDTOResp.paraDTO(evento);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /*************************** OBTER ITENS VENDIDOS ***************************/

  @GetMapping("/{id}/itens-vendidos")
  public ResponseEntity<EventoDTORespVendas> obterItensVendidos(
      @PathVariable Long id) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    EventoDTORespVendas resp = EventoDTORespVendas.paraDTO(evento);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /************************** PODE ALTERAR/EXCLUIR? ***************************/

  @GetMapping("/alterar/{id}")
  public ResponseEntity<?> podeAlterar(@PathVariable Long id) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    return evento.possuiIngressosVendidos()
        ? new ResponseEntity<>(HttpStatus.CONFLICT)
        : new ResponseEntity<>(HttpStatus.OK);
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

    boolean inativou = eventoService.inativar(pesq.get());

    if (!inativou)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /******************************* PAUSAR VENDA *******************************/

  @PatchMapping("/{id}/pausar-venda/{pausar}")
  public ResponseEntity<?> pausarVenda(@PathVariable Long id,
                                       @PathVariable Boolean pausar) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    if (pausar)
      eventoService.pausarVenda(evento);
    else
      eventoService.despausarVenda(evento);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**************************** CADASTRAR DESPESA *****************************/

  @PostMapping("/{id}/despesas")
  public ResponseEntity<?> cadastrarDespesa(
      @PathVariable Long eventoId, @RequestBody @Valid DespesaDTO dto) {

    Optional<Evento> pesq = eventoService.obterPorId(eventoId, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    boolean resp = eventoService.adicionarDespesa(evento, dto.paraObjeto());

    return new ResponseEntity<>(resp ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
  }

  /****************************** OBTER DESPESAS ******************************/

  @GetMapping("/{id}/despesas")
  public ResponseEntity<EventoDTORespDespesa> obterDespesas(
      @PathVariable Long id) {

    Optional<Evento> pesq = eventoService.obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    Evento evento = pesq.get();

    EventoDTORespDespesa resp = EventoDTORespDespesa.paraDTO(evento);

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }
}
