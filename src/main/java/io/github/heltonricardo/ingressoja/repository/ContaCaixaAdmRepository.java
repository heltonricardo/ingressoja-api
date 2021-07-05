package io.github.heltonricardo.ingressoja.repository;

import io.github.heltonricardo.ingressoja.model.ContaCaixaAdm;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaCaixaAdmRepository
		extends PagingAndSortingRepository<ContaCaixaAdm, Integer> {
}
