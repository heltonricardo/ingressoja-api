package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Despesa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository
    extends PagingAndSortingRepository<Despesa, Long> {
}
