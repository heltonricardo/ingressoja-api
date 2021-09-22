package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.Administrador;
import io.github.heltonricardo.ingressoja.repository.AdministradorRepository;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {

  private final AdministradorRepository administradorRepository;
  private final ValidacaoService validacaoService;

  @Autowired
  public AdministradorService(AdministradorRepository administradorRepository,
                              ValidacaoService validacaoService) {
    this.administradorRepository = administradorRepository;
    this.validacaoService = validacaoService;
  }

  /******************************* OBTER TODOS ********************************/

  public Iterable<Administrador> obterTodos() {

    return administradorRepository.findAll();
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<Administrador> obterPorId(Long id, boolean usarFiltro) {

    return usarFiltro
        ? administradorRepository.findByIdAndAtivoTrue(id)
        : administradorRepository.findById(id);
  }

  /********************************** SALVAR **********************************/

  public Administrador salvar(Administrador administrador) {

    if (validacaoService
        .emailJaCadastrado(administrador.getUsuario().getEmail()))
      return null;

    return administradorRepository.save(administrador);
  }

  /******************************** ATUALIZAR *********************************/

  public Administrador atualizar(Administrador administrador, Long id) {

    Optional<Administrador> pesq = obterPorId(id, UsarFiltro.SIM);

    if (pesq.isEmpty())
      return null;

    Administrador legado = pesq.get();

    String emailLegado = legado.getUsuario().getEmail();

    // Se deseja atualizar o e-mail, mas esse já existe em outro cadastro
    if (!emailLegado.equals(administrador.getUsuario().getEmail()) &&
        validacaoService
            .emailJaCadastrado(administrador.getUsuario().getEmail()))
      return null;

    legado.getUsuario().setEmail(administrador.getUsuario().getEmail());
    legado.getUsuario().setSenha(administrador.getUsuario().getSenha());

    return administradorRepository.save(legado);
  }

  /********************************* INATIVAR *********************************/

  public void inativar(Administrador administrador) {

    administradorRepository.deleteById(administrador.getId());
  }
}
