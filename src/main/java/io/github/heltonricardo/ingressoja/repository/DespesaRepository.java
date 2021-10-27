package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.Despesa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DespesaRepository
    extends PagingAndSortingRepository<Despesa, Long> {
}
