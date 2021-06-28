package io.github.heltonricardo.ingressoja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.heltonricardo.ingressoja.model.Saque;

@Repository
public interface SaqueRepository
		extends PagingAndSortingRepository<Saque, Long> {
}
