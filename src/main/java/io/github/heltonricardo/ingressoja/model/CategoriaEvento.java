package io.github.heltonricardo.ingressoja.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "ativo")
public class CategoriaEvento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private Boolean ativo = true;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaEvento")
  private List<Evento> eventos;

  public CategoriaEvento(String nome) {
    this.nome = nome;
  }

  public void adicionaEvento(Evento evento) {
    eventos.add(evento);
  }
}
