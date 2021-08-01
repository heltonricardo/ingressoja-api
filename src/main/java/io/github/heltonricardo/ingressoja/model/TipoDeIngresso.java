package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "ativo")
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
  private Boolean ativo = true;

  @ManyToOne
  private Evento evento;

  public TipoDeIngresso(String nome, Double valor, String descricao,
                        Integer quantidadeTotal) {
    this.nome = nome;
    this.valor = valor;
    this.descricao = descricao;
    this.quantidadeTotal = quantidadeTotal;
    this.quantidadeDisponivel = quantidadeTotal;
  }
}
