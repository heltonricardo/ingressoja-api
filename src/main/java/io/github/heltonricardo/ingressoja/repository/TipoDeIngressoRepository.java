package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDeIngressoRepository
    extends PagingAndSortingRepository<TipoDeIngresso, Long> {
}
