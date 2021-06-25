package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.ContaCaixaAdm;

public interface ContaCaixaAdmRepository
		extends PagingAndSortingRepository<ContaCaixaAdm, Integer> {
}
