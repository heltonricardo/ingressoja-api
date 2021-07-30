package io.github.heltonricardo.ingressoja.dto_in;

import io.github.heltonricardo.ingressoja.model.Evento;
import io.github.heltonricardo.ingressoja.model.TipoDeIngresso;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class EventoDTO {

  @NotBlank
  @Size(max = 255)
  private String titulo;

  @Future
  @NotNull
  private Date inicio;

  @Future
  @NotNull
  private Date termino;

  @NotBlank
  @Size(max = 2000)
  private String descricao;

  @NotNull
  private Boolean online;

  @Size(max = 1000)
  private String url;

  @Size(max = 100)
  private String logradouro;

  @Size(max = 10)
  private String numero;

  @Size(max = 50)
  private String bairro;

  @Size(max = 50)
  private String cidade;

  @Size(max = 2)
  private String uf;

  @Size(max = 8)
  private String cep;

  @NotNull
  @PositiveOrZero
  private Long idProdutora;

  @NotNull
  @PositiveOrZero
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
