package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comprador")
@SQLDelete(sql = "UPDATE comprador SET ativo = false WHERE id = ?")
public class Comprador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(length = 11, unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Usuario usuario;

  @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
  private List<Pedido> pedidos;

  public Comprador(String nome, String cpf, Usuario usuario) {
    this.nome = nome;
    this.cpf = cpf;
    this.usuario = usuario;
  }
}
