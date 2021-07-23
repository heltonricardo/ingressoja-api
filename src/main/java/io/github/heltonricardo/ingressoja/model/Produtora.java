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
  private String email;
  private String nomeFantasia;
  private String razaoSocial;
  private String cnpj;

  private String banco;
  private String agencia;
  private String conta;

  private Double valorCarteira = 0.0;
  private Boolean ativo = true;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "produtora")
  private List<Evento> eventos;

  @ManyToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Saque> saques;

  public Produtora(String email, String nomeFantasia, String razaoSocial,
                   String cnpj, String banco, String agencia, String conta,
                   Usuario usuario) {
    this.email = email;
    this.nomeFantasia = nomeFantasia;
    this.razaoSocial = razaoSocial;
    this.cnpj = cnpj;
    this.banco = banco;
    this.agencia = agencia;
    this.conta = conta;
    this.usuario = usuario;
  }
}
