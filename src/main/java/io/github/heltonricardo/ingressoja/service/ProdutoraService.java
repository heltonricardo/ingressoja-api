package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.ProdutoraRepository;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoraService {

  private final ProdutoraRepository produtoraRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public ProdutoraService(ProdutoraRepository produtoraRepository,
                          ValidacaoService validacaoService) {
    this.produtoraRepository = produtoraRepository;
    this.validacaoService = validacaoService;
  }

  public Iterable<Produtora> obterTodas() {
    return produtoraRepository.findAll();
  }

  public Optional<Produtora> obterPorId(Long id, boolean usarFiltro) {
    return usarFiltro
        ? produtoraRepository.findByIdAndAtivoTrue(id)
        : produtoraRepository.findById(id);
  }

  public Optional<Produtora> obterPorCnpj(String cnpj) {
    return produtoraRepository.findByCnpj(cnpj);
  }

  public List<Evento> obterEventos(Long id) {

    Optional<Produtora> pesq = obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return null;

    Produtora produtora = pesq.get();

    return produtora.getEventos();
  }

  public Produtora salvar(Produtora produtora) {
    if (validacaoService.emailJaCadastrado(produtora.getUsuario().getEmail()))
      return null;

    if (validacaoService.cnpjJaCadastrado((produtora.getCnpj())))
      return null;

    return produtoraRepository.save(produtora);
  }

  /******************************** ATUALIZAR *********************************/

  public Produtora atualizar(Produtora produtora, Long id) {

    Optional<Produtora> pesq = obterPorId(id, UsarFiltro.NAO);

    if (pesq.isEmpty())
      return null;

    Produtora legado = pesq.get();

    String emailLegado = legado.getUsuario().getEmail();

    // Se deseja atualizar o e-mail, mas esse já existe em outro cadastro
    if (!emailLegado.equals(produtora.getUsuario().getEmail()) &&
        validacaoService.emailJaCadastrado(produtora.getUsuario().getEmail()))
      return null;

    legado.setBanco(produtora.getBanco());
    legado.setConta(produtora.getConta());
    legado.setAgencia(produtora.getAgencia());
    legado.setNomeFantasia(produtora.getNomeFantasia());
    legado.getUsuario().setEmail(produtora.getUsuario().getEmail());
    legado.getUsuario().setSenha(produtora.getUsuario().getSenha());

    return produtoraRepository.save(legado);
  }

  /********************************* INATIVAR *********************************/

  public void inativar(Produtora produtora) {

    produtoraRepository.deleteById(produtora.getId());
  }
}
