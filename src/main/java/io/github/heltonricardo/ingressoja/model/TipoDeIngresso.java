package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TipoDeIngresso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 30, nullable = false)
  private String nome;

  @Column(nullable = false)
  private Double valor;

  @Column(length = 50)
  private String descricao;

  @Column(nullable = false)
  private Integer quantidadeTotal;

  @Column(nullable = false)
  private Integer quantidadeDisponivel;

  @Column(nullable = false)
  private Date inicio;

  @Column(nullable = false)
  private Date termino;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToMany(mappedBy = "tipoDeIngresso")
  private List<ItemPedido> itensPedido;

  @ManyToOne
  private Evento evento;

  /******************************** CONSTRUTOR ********************************/

  public TipoDeIngresso(String nome, Double valor, String descricao,
                        Integer quantidadeTotal, Date inicio, Date termino) {
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
    this.quantidadeTotal = quantidadeTotal;
    this.quantidadeDisponivel = quantidadeTotal;
    this.inicio = inicio;
    this.termino = termino;
  }
}
