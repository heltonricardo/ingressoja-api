package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.IngressoDisponivel;

public interface IngressoDisponivelRepository
		extends PagingAndSortingRepository<IngressoDisponivel, Long> {
}
