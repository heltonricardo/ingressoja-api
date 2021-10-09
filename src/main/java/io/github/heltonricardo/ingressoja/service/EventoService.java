package io.github.heltonricardo.ingressoja.service;

import io.github.heltonricardo.ingressoja.model.CategoriaEvento;
import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.Produtora;
import io.github.heltonricardo.ingressoja.repository.EventoRepository;
import io.github.heltonricardo.ingressoja.utils.Formatador;
import io.github.heltonricardo.ingressoja.utils.S3Connector;
import io.github.heltonricardo.ingressoja.utils.UsarFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventoService {

  private final EventoRepository eventoRepository;
  private final ProdutoraService produtoraService;
  private final CategoriaEventoService categoriaEventoService;
  private final TipoDeIngressoService tipoDeIngressoService;

  @Autowired
  public EventoService(EventoRepository eventoRepository,
                       ProdutoraService produtoraService,
                       CategoriaEventoService categoriaEvento,
                       TipoDeIngressoService tipoDeIngressoService) {
    this.eventoRepository = eventoRepository;
    this.produtoraService = produtoraService;
    this.categoriaEventoService = categoriaEvento;
    this.tipoDeIngressoService = tipoDeIngressoService;
  }

  public boolean possuiIngressosVendidos(Evento evento) {

    return evento.getTiposDeIngresso().stream()
        .anyMatch(t -> t.getQuantidadeDisponivel().intValue()
            != t.getQuantidadeTotal().intValue());
  }

  /******************************* OBTER TODOS ********************************/

  public Iterable<Evento> obterTodos() {

    return eventoRepository.findByTerminoGreaterThanEqual(new Date());
  }

  /******************************* OBTER POR ID *******************************/

  public Optional<Evento> obterPorId(Long id, boolean usarFiltro) {

    return usarFiltro
        ? eventoRepository.findByIdAndAtivoTrue(id)
        : eventoRepository.findById(id);
  }

  /********************************** SALVAR **********************************/

  public Evento salvar(Evento evento, MultipartFile file) {

    Optional<Produtora> pesqProdutora =
        produtoraService.obterPorId(evento.getIdProdutora(), UsarFiltro.NAO);

    Optional<CategoriaEvento> pesqCategoria =
        categoriaEventoService.obterPorId(evento.getIdCategoria(),
            UsarFiltro.SIM);

    if (pesqProdutora.isEmpty() || pesqCategoria.isEmpty())
      return null;

    if (!Formatador.isImagem(file.getOriginalFilename()))
      return null;

    Produtora produtora = pesqProdutora.get();
    CategoriaEvento categoriaEvento = pesqCategoria.get();
    evento.setProdutora(produtora);
    evento.setCategoriaEvento(categoriaEvento);
    evento.getTiposDeIngresso().forEach(t -> t.setEvento(evento));

    Long id = eventoRepository.save(evento).getId();

    String urlImagem = S3Connector.upload(file,
        Formatador.nomeArquivo(id,
            Objects.requireNonNull(file.getOriginalFilename())));

    evento.setImagemURL(urlImagem);
    eventoRepository.save(evento);

    return evento;
  }

  /******************************** ATUALIZAR *********************************/

  public Evento atualizar(Evento evento, MultipartFile file, Long id) {

    Optional<Evento> pesqEvento = obterPorId(id, UsarFiltro.SIM);

    Optional<Produtora> pesqProdutora =
        produtoraService.obterPorId(evento.getIdProdutora(), UsarFiltro.NAO);

    Optional<CategoriaEvento> pesqCategoria =
        categoriaEventoService.obterPorId(evento.getIdCategoria(),
            UsarFiltro.SIM);

    if (pesqEvento.isEmpty() || pesqProdutora.isEmpty()
        || pesqCategoria.isEmpty())
      return null;

    if (file != null && !Formatador.isImagem(file.getOriginalFilename()))
      return null;

    Evento legado = pesqEvento.get();

    if (possuiIngressosVendidos(legado))
      return null;

    legado.setUf(evento.getUf());
    legado.setCep(evento.getCep());
    legado.setUrl(evento.getUrl());
    legado.setBairro(evento.getBairro());
    legado.setCidade(evento.getCidade());
    legado.setInicio(evento.getInicio());
    legado.setNumero(evento.getNumero());
    legado.setOnline(evento.getOnline());
    legado.setTitulo(evento.getTitulo());
    legado.setTermino(evento.getTermino());
    legado.setDescricao(evento.getDescricao());
    legado.setLogradouro(evento.getLogradouro());
    legado.setCategoriaEvento(pesqCategoria.get());

    legado.getTiposDeIngresso().forEach(tipoDeIngressoService::remover);
    legado.setTiposDeIngresso(evento.getTiposDeIngresso());
    legado.getTiposDeIngresso().forEach(t -> t.setEvento(legado));

    if (file != null) {
      String urlImagem = S3Connector.upload(file, Formatador.nomeArquivo(id,
          Objects.requireNonNull(file.getOriginalFilename())));
      legado.setImagemURL(urlImagem);
    }

    return eventoRepository.save(legado);
  }

  /********************************* INATIVAR *********************************/

  public boolean inativar(Evento evento) {

    if (possuiIngressosVendidos(evento))
      return false;

    eventoRepository.deleteById(evento.getId());
    return true;
  }
}
