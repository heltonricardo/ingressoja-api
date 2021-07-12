package io.github.heltonricardo.ingressoja.controller;

import io.github.heltonricardo.ingressoja.dto.AdministradorDTO;
import io.github.heltonricardo.ingressoja.dto.resp.AdministradorDTOResp;
import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("administrador")
public class AdministradorController {

  private final AdministradorService administradorService;

  @Autowired
  public AdministradorController(AdministradorService administradorService) {
    this.administradorService = administradorService;
  }

  /******************************* OBTER TODOS ********************************/

  @GetMapping
  public ResponseEntity<List<AdministradorDTOResp>> obterTodos() {

    List<AdministradorDTOResp> resp = new ArrayList<>();

    administradorService.obterTodos()
        .forEach(c -> resp.add(AdministradorDTOResp.paraDTO(c)));

    return new ResponseEntity<>(resp, HttpStatus.OK);
  }

  /********************************** SALVAR **********************************/

  @PostMapping
  public ResponseEntity<AdministradorDTOResp> salvar(
      @RequestBody AdministradorDTO dto) {

    Administrador resp = administradorService.salvar(dto.paraObjeto());

    if (resp == null)
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    
    return new ResponseEntity<>(AdministradorDTOResp.paraDTO(resp),
        HttpStatus.CREATED);
  }
}
