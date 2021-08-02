package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "ativo")
public class Produtora {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nomeFantasia;

  @Column(nullable = false)
  private String razaoSocial;

  @Column(length = 14, nullable = false)
  private String cnpj;

  @Column(length = 50, nullable = false)
  private String banco;

  @Column(length = 20, nullable = false)
  private String agencia;

  @Column(length = 20, nullable = false)
  private String conta;

  @Column(nullable = false)
  private Double valorCarteira = 0.0;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Evento> eventos;

  @OneToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Saque> saques;

  public Produtora(String nomeFantasia, String razaoSocial,
                   String cnpj, String banco, String agencia, String conta,
                   Usuario usuario) {
    this.nomeFantasia = nomeFantasia;
    this.razaoSocial = razaoSocial;
    this.cnpj = cnpj;
    this.banco = banco;
    this.agencia = agencia;
    this.conta = conta;
    this.usuario = usuario;
  }
}
