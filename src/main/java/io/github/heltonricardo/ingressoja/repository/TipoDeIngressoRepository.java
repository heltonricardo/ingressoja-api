package io.github.heltonricardo.ingressoja.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;

@Repository
public interface TipoDeIngressoRepository
		extends PagingAndSortingRepository<TipoDeIngresso, Long> {
}
