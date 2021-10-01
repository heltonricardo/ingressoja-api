package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "evento")
@SQLDelete(sql = "UPDATE evento SET ativo = false WHERE id = ?")
public class Evento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String titulo;

  @Column(length = 1000, nullable = false)
  private String imagemURL = "";

  @Column(nullable = false)
  private Date inicio;

  @Column(nullable = false)
  private Date termino;

  @Column(length = 2000, nullable = false)
  private String descricao;

  @Column(nullable = false)
  private Boolean online;

  @Column(length = 1000)
  private String url;

  @Column(length = 100)
  private String logradouro;

  @Column(length = 10)
  private String numero;

  @Column(length = 50)
  private String bairro;

  @Column(length = 50)
  private String cidade;

  @Column(length = 2)
  private String uf;

  @Column(length = 8)
  private String cep;

  @Column(nullable = false)
  private Boolean ativo = true;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Produtora produtora;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private CategoriaEvento categoriaEvento;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "evento")
  private List<TipoDeIngresso> tiposDeIngresso;

  @Transient
  private Long idProdutora;

  @Transient
  private Long idCategoria;

  public Evento(String titulo, Date inicio, Date termino, String descricao,
                Boolean online, String url, String logradouro, String numero,
                String bairro, String cidade, String uf, String cep,
                List<TipoDeIngresso> tiposDeIngresso, Long idProdutora,
                Long idCategoria) {
    this.titulo = titulo;
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
    this.cep = cep;
    this.tiposDeIngresso = tiposDeIngresso;
    this.idProdutora = idProdutora;
    this.idCategoria = idCategoria;
  }
}
