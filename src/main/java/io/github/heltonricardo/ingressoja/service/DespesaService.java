package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.Despesa;
import io.github.heltonricardo.ingressoja.repository.DespesaRepository;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DespesaService {

  private final DespesaRepository despesaRepository;

  @Autowired
  public DespesaService(DespesaRepository despesaRepository) {
    this.despesaRepository = despesaRepository;
  }

  /******************************** ATUALIZAR *********************************/

  public Despesa atualizar(Despesa despesa, Long id) {

    Optional<Despesa> pesq = despesaRepository.findById(id);

    if (pesq.isEmpty())
      return null;

    Despesa legado = pesq.get();

    legado.setValor(despesa.getValor());
    legado.setDescricao(despesa.getDescricao());

    return despesaRepository.save(legado);
  }

  /********************************* EXCLUIR **********************************/

  public void inativar(Despesa despesa) {

    despesaRepository.deleteById(despesa.getId());
  }
}
