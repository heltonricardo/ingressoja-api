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

  public Usuario(String senha) {
    this.senha = senha;
  }
}
