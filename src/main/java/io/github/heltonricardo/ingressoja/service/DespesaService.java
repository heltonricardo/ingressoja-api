package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Despesa;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.repository.DespesaRepository;
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

  /******************************* OBTER POR ID *******************************/

  public Optional<Despesa> obterPorId(Long id) {

    return despesaRepository.findById(id);
  }

  /********************************** SALVAR **********************************/

  public Despesa salvar(Despesa despesa, Evento evento) {

    despesa.setEvento(evento);
    return despesaRepository.save(despesa);
  }

  public Despesa salvar(Despesa despesa) {

    return despesaRepository.save(despesa);
  }

  /******************************** ATUALIZAR *********************************/

  public Despesa atualizar(Despesa despesa, Long id) {

    Optional<Despesa> pesq = obterPorId(id);

    if (pesq.isEmpty())
      return null;

    Despesa legado = pesq.get();

    legado.setValor(despesa.getValor());
    legado.setDescricao(despesa.getDescricao());

    return despesaRepository.save(legado);
  }

  /********************************* EXCLUIR **********************************/

  public void excluir(Despesa despesa) {

    despesaRepository.deleteById(despesa.getId());
  }
}
