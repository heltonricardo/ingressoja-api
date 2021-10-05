package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto_in.CategoriaEventoDTO;
import io.github.heltonricardo.ingressoja.dto_out.CategoriaEventoDTORespSimples;
import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.service.CategoriaEventoService;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  public ResponseEntity<List<CategoriaEventoDTORespSimples>> obterTodas() {

    List<CategoriaEventoDTORespSimples> resp = new ArrayList<>();

    categoriaEventoService.obterTodas(UsarFiltro.SIM)
        .forEach(c -> resp.add(CategoriaEventoDTORespSimples.paraDTO(c)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /******************************* OBTER POR ID *******************************/

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaEventoDTORespSimples> obterPorId(
      @PathVariable Long id) {

    Optional<CategoriaEvento> pesq = categoriaEventoService.obterPorId(id,
        UsarFiltro.NAO);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    CategoriaEventoDTORespSimples resp =
        CategoriaEventoDTORespSimples.paraDTO(pesq.get());

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<CategoriaEventoDTORespSimples> salvar(
      @RequestBody @Valid CategoriaEventoDTO dto) {

    CategoriaEvento resp = categoriaEventoService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(CategoriaEventoDTORespSimples.paraDTO(resp),
        HttpStatus.CREATED);
  }

  /******************************** ATUALIZAR *********************************/

  @PutMapping("/{id}")
  public ResponseEntity<CategoriaEventoDTORespSimples> atualizar(
      @RequestBody @Valid CategoriaEventoDTO dto, @PathVariable Long id) {

    CategoriaEvento resp =
        categoriaEventoService.atualizar(dto.paraObjeto(), id);

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);

    return new ResponseEntity<>(CategoriaEventoDTORespSimples.paraDTO(resp),
        HttpStatus.OK);
  }

  /********************************* INATIVAR *********************************/

  @DeleteMapping("/{id}")
  public ResponseEntity<CategoriaEventoDTORespSimples> inativar(
      @PathVariable Long id) {

    Optional<CategoriaEvento> pesq = categoriaEventoService.obterPorId(id,
        UsarFiltro.SIM);

    if (pesq.isEmpty())
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    categoriaEventoService.inativar(pesq.get());

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
