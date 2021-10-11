package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import io.github.heltonricardo.ingressoja.validator.EventoOnline;
import io.github.heltonricardo.ingressoja.validator.EventoPresencial;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class EventoDTO {

  @Size(max = 255)
  @NotBlank(groups = Default.class)
  private String titulo;

  @Future(groups = Default.class)
  @NotNull(groups = Default.class)
  private Date inicio;

  @Future(groups = Default.class)
  @NotNull(groups = Default.class)
  private Date termino;

  @NotBlank(groups = Default.class)
  @Size(max = 2000, groups = Default.class)
  private String descricao;

  @NotNull(groups = Default.class)
  private Boolean online;

  @URL(groups = EventoOnline.class)
  @NotBlank(groups = EventoOnline.class)
  @Size(max = 1000, groups = EventoOnline.class)
  private String url;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 100, groups = EventoPresencial.class)
  private String logradouro;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 10, groups = EventoPresencial.class)
  private String numero;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 50, groups = EventoPresencial.class)
  private String bairro;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 50, groups = EventoPresencial.class)
  private String cidade;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 2, groups = EventoPresencial.class)
  private String uf;

  @NotBlank(groups = EventoPresencial.class)
  @Size(max = 8, groups = EventoPresencial.class)
  private String cep;

  @NotNull(groups = Default.class)
  @Positive(groups = Default.class)
  private Integer totalIngressos;

  @NotNull(groups = Default.class)
  @PositiveOrZero(groups = Default.class)
  private Long idProdutora;

  @NotNull(groups = Default.class)
  @PositiveOrZero(groups = Default.class)
  private Long idCategoria;

  @NotEmpty
  private List<@Valid TipoDeIngressoDTO> tiposDeIngresso;

  /************************* CONVERSÃO DTO -> OBJETO **************************/

  public Evento paraObjeto() {
    List<TipoDeIngresso> tipos = new ArrayList<>();

    tiposDeIngresso.forEach(t -> tipos.add(t.paraObjeto()));

    return new Evento(titulo, inicio, termino, descricao, online,
        url, logradouro, numero, bairro, cidade, uf, cep, totalIngressos, tipos,
        idProdutora, idCategoria);
  }
}
