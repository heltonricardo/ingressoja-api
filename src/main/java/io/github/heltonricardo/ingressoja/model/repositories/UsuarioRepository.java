package io.github.heltonricardo.ingressoja.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.github.heltonricardo.ingressoja.model.entities.Usuario;

public interface UsuarioRepository
		extends PagingAndSortingRepository<Usuario, Long> {
}
