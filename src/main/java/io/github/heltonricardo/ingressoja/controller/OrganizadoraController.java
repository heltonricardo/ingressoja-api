package io.github.heltonricardo.ingressoja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping
	public ResponseEntity<Organizadora> salvar(@RequestBody OrganizadoraDTO dto) {
		Organizadora organizadora = organizadoraService.salvar(dto.paraObjeto());
		return new ResponseEntity<>(organizadora, HttpStatus.CREATED);
	}
}

//@GetMapping("/{id}")
//public Optional<Organizadora> obterOrganizadoraPorId(@PathVariable Long id) {
//	return organizadoraRepository.findById(id);
//}
//	@GetMapping
//	public Iterable<Organizadora> obterOrganizadorasPorPagina() {
//		return organizadoraRepository.findAll();
//	}
//
////	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
////	public ResponseEntity<?> salvarOrganizadora() {		
////	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> excluirOrganizadora(@PathVariable Long id) {
//		try {
//			Organizadora pesq = obterOrganizadoraPorId(id).get();
//			pesq.setAtivo(false);
//			organizadoraRepository.save(pesq);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping("/{id}/saque")
//	public ResponseEntity<?> novoSaque(@PathVariable Long id,
//			@RequestBody @Valid Saque saque) {
//		try {
//			Organizadora pesq = obterOrganizadoraPorId(id).get();
//			if (pesq == null) {
//				throw new Exception();
//			}
//			pesq.addSaque(saque);
//			organizadoraRepository.save(pesq);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//}