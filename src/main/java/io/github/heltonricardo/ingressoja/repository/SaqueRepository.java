package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Saque;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaqueRepository
    extends PagingAndSortingRepository<Saque, Long> {
}
