package io.github.heltonricardo.ingressoja.models.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.models.entities.Usuario;

public interface UsuarioRepository
		extends PagingAndSortingRepository<Usuario, Long> {
}
