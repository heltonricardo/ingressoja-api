package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categoria_evento")
@SQLDelete(sql = "UPDATE categoria_evento SET ativo = false WHERE id = ?")
public class CategoriaEvento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, unique = true, nullable = false)
  private String nome;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "categoriaEvento")
  private List<Evento> eventos;

  public CategoriaEvento(String nome) {
    this.nome = nome;
  }
}
