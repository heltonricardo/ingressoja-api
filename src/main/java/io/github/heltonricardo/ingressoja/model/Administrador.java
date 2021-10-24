package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "administrador")
@SQLDelete(sql = "UPDATE administrador SET ativo = false WHERE id = ?")
public class Administrador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Usuario usuario;

  public Administrador(String nome, Usuario usuario) {
    this.nome = nome;
    this.usuario = usuario;
  }
}
