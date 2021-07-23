package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository
    extends PagingAndSortingRepository<Usuario, Long> {
}
