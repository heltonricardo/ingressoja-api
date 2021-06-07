package io.github.heltonricardo.ingressoja.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.entities.Organizadora;
import io.github.heltonricardo.ingressoja.model.repositories.OrganizadoraRepository;

@RestController
@RequestMapping("organizadora")
public class OrganizadoraController {

	@Autowired
	private OrganizadoraRepository organizadoraRepository;

	@GetMapping("/{id}")
	public Optional<Organizadora> obterOrganizadoraPorId(
			@PathVariable Long id) {
		return organizadoraRepository.findById(id);
	}

	@GetMapping("/pagina/{numeroPagina}/{quantidade}")
	public Iterable<Organizadora> obterOrganizadorasPorPagina(
			@PathVariable int numeroPagina, @PathVariable int quantidade) {
		quantidade = (quantidade > 10) ? 10 : quantidade;
		Pageable pagina = PageRequest.of(numeroPagina, quantidade);
		return organizadoraRepository.findAll(pagina);
	}

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> editarOrganizadora(
			@RequestBody @Valid Organizadora organizadora) {
		try {
			organizadoraRepository.save(organizadora);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirOrganizadora(@PathVariable Long id) {
		try {
			Organizadora pesq = obterOrganizadoraPorId(id).get();
			pesq.setAtivo(false);
			organizadoraRepository.save(pesq);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}