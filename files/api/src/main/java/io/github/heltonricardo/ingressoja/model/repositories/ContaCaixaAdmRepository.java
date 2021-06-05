package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.ContaCaixaAdm;

public interface ContaCaixaAdmRepository
		extends PagingAndSortingRepository<ContaCaixaAdm, Integer> {
}
