package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.validator.EventoOnline;
import io.github.heltonricardo.ingressoja.validator.EventoPresencial;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class EventoDTO {

  @NotBlank(groups = {EventoOnline.class, EventoPresencial.class})
  @Size(max = 255, groups = {EventoOnline.class, EventoPresencial.class})
  private String titulo;

  @Future(groups = {EventoOnline.class, EventoPresencial.class})
  @NotNull(groups = {EventoOnline.class, EventoPresencial.class})
  private Date inicio;

  @Future(groups = {EventoOnline.class, EventoPresencial.class})
  @NotNull(groups = {EventoOnline.class, EventoPresencial.class})
  private Date termino;

  @NotBlank(groups = {EventoOnline.class, EventoPresencial.class})
  @Size(max = 2000, groups = {EventoOnline.class, EventoPresencial.class})
  private String descricao;

  @NotNull(groups = {EventoOnline.class, EventoPresencial.class})
  private Boolean online;

  @Size(max = 1000, groups = EventoOnline.class)
  @NotBlank(groups = EventoOnline.class)
  @URL(groups = EventoOnline.class)
  private String url;

  @Size(max = 100, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String logradouro;

  @Size(max = 10, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String numero;

  @Size(max = 50, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String bairro;

  @Size(max = 50, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String cidade;

  @Size(max = 2, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String uf;

  @Size(max = 8, groups = EventoPresencial.class)
  @NotBlank(groups = EventoPresencial.class)
  private String cep;

  @NotNull(groups = {EventoOnline.class, EventoPresencial.class})
  @PositiveOrZero(groups = {EventoOnline.class, EventoPresencial.class})
  private Long idProdutora;

  @NotNull(groups = {EventoOnline.class, EventoPresencial.class})
  @PositiveOrZero(groups = {EventoOnline.class, EventoPresencial.class})
  private Long idCategoria;

  @Valid
  private List<TipoDeIngressoDTO> tiposDeIngresso;

  /************************* CONVERSÃO DTO -> OBJETO **************************/

  public Evento paraObjeto() {
    List<TipoDeIngresso> tipos = new ArrayList<>();

    tiposDeIngresso.forEach(t -> tipos.add(t.paraObjeto()));

    return new Evento(titulo, inicio, termino, descricao, online,
        url, logradouro, numero, bairro, cidade, uf, cep, tipos, idProdutora,
        idCategoria);
  }
}
