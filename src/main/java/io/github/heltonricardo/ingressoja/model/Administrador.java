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

  @OneToMany(cascade = CascadeType.ALL)
  List<Saque> saques;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String email;
  private Boolean ativo = true;
  @ManyToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

  public Administrador(String nome, String email, Usuario usuario) {
    this.nome = nome;
    this.email = email;
    this.usuario = usuario;
  }
}
