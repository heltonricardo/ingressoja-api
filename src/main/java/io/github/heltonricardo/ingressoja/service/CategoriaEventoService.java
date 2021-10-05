package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.repository.CategoriaEventoRepository;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaEventoService {

  private final CategoriaEventoRepository categoriaEventoRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public CategoriaEventoService(
      CategoriaEventoRepository categoriaEventoRepository,
      ValidacaoService validacaoService) {

    this.categoriaEventoRepository = categoriaEventoRepository;
    this.validacaoService = validacaoService;
  }

  /******************************* OBTER TODAS ********************************/

  public Iterable<CategoriaEvento> obterTodas(boolean usarFiltro) {

    return usarFiltro ?
        categoriaEventoRepository.findByAtivoTrue()
        : categoriaEventoRepository.findAll();
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<CategoriaEvento> obterPorId(Long id, boolean usarFiltro) {

    return usarFiltro
        ? categoriaEventoRepository.findByIdAndAtivoTrue(id)
        : categoriaEventoRepository.findById(id);
  }

  /****************************** OBTER POR NOME ******************************/

  public Optional<CategoriaEvento> obterPorNome(String nome) {

    return categoriaEventoRepository.findByNome(nome);
  }

  /********************************** SALVAR **********************************/

  public CategoriaEvento salvar(CategoriaEvento categoriaEvento) {

    if (validacaoService.categoriaJaCadastrada(categoriaEvento.getNome()))
      return null;

    return categoriaEventoRepository.save(categoriaEvento);
  }

  /******************************** ATUALIZAR *********************************/

  public CategoriaEvento atualizar(CategoriaEvento categoriaEvento, Long id) {

    if (validacaoService.categoriaJaCadastrada(categoriaEvento.getNome()))
      return null;

    Optional<CategoriaEvento> pesq = obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return null;

    CategoriaEvento legado = pesq.get();

    legado.setNome(categoriaEvento.getNome());

    return categoriaEventoRepository.save(legado);
  }

  /********************************* INATIVAR *********************************/

  public void inativar(CategoriaEvento categoriaEvento) {

    categoriaEventoRepository.deleteById(categoriaEvento.getId());
  }
}
