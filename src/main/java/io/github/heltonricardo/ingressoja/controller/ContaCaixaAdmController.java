package io.github.heltonricardo.ingressoja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.heltonricardo.ingressoja.model.ContaCaixaAdm;
import io.github.heltonricardo.ingressoja.repository.ContaCaixaAdmRepository;

@RestController
@RequestMapping("conta-caixa-adm")
public class ContaCaixaAdmController {

	@Autowired
	private ContaCaixaAdmRepository contaCaixaAdmRepository;

	@GetMapping
	public Optional<ContaCaixaAdm> obterContaCaixaAdm() {
		return contaCaixaAdmRepository.findById(1);
	}

	@PostMapping
	public void obterContaCaixaAdm(
			@RequestBody @Valid ContaCaixaAdm contaCaixaAdm) {
		if (obterContaCaixaAdm().isEmpty()) {
			contaCaixaAdmRepository.save(contaCaixaAdm);
		}
	}

	@PutMapping
	public void alterarContaCaixaAdm(
			@RequestBody @Valid ContaCaixaAdm contaCaixaAdm) {
		contaCaixaAdm.setId(1);
		contaCaixaAdmRepository.save(contaCaixaAdm);
	}
}
