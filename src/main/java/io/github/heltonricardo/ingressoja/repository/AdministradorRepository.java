package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Administrador;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository
    extends PagingAndSortingRepository<Administrador, Long> {
}
