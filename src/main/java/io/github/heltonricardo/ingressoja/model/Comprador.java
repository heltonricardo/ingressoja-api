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
public class Comprador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(length = 11, unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private Boolean ativo = true;

  @ManyToOne(cascade = CascadeType.ALL)
  private Usuario usuario;

  @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
  private List<Pedido> pedidos;

  public Comprador(String nome, String email, String cpf, Usuario usuario) {
    this.nome = nome;
    this.email = email;
    this.cpf = cpf;
    this.usuario = usuario;
  }
}
