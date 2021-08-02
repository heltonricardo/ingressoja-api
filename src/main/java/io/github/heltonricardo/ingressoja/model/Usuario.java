package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String senha;

  @Column(unique = true, nullable = false)
  private String email;

  @OneToOne(mappedBy = "usuario")
  private Administrador administrador;

  @OneToOne(mappedBy = "usuario")
  private Comprador comprador;

  @OneToOne(mappedBy = "usuario")
  private Produtora produtora;

  public Usuario(String senha, String email) {
    this.senha = senha;
    this.email = email;
  }
}
