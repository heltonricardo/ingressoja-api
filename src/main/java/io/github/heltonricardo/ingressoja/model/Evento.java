package io.github.heltonricardo.ingressoja.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "ativo")
@SQLDelete(sql = "UPDATE evento SET ativo = false WHERE id = ?")
public class Evento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String imagemURL;
  private LocalDateTime inicio;
  private LocalDateTime termino;

  @Column(length = 1000)
  private String descricao;
  private Boolean online;
  private String url;
  private String logradouro;
  private String numero;
  private String bairro;
  private String cidade;
  private String uf;
  private String pais;
  private String cep;
  private Boolean ativo = true;

  @ManyToOne(cascade = CascadeType.ALL)
  private Produtora produtora;

  @ManyToOne(cascade = CascadeType.ALL)
  private CategoriaEvento categoriaEvento;

  @OneToMany(cascade = CascadeType.ALL)
  private List<TipoDeIngresso> tiposDeIngresso;

  public Evento(String titulo, String imagemURL, LocalDateTime inicio,
                LocalDateTime termino, String descricao, Boolean online,
                String url, String logradouro, String numero, String bairro,
                String cidade, String uf, String pais, String cep,
                List<TipoDeIngresso> tiposDeIngresso) {
    this.titulo = titulo;
    this.imagemURL = imagemURL;
    this.inicio = inicio;
    this.termino = termino;
    this.descricao = descricao;
    this.online = online;
    this.url = url;
    this.logradouro = logradouro;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.pais = pais;
    this.cep = cep;
    this.tiposDeIngresso = tiposDeIngresso;
  }
}
