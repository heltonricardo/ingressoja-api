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
public class Administrador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private Boolean ativo = true;

  @ManyToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToMany(cascade = CascadeType.ALL)
  List<Saque> saques;

  public Administrador(String nome, String email, Usuario usuario) {
    this.nome = nome;
    this.email = email;
    this.usuario = usuario;
  }
}
