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
@Table(name = "produtora")
@SQLDelete(sql = "UPDATE produtora SET ativo = false WHERE id = ?")
public class Produtora {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nomeFantasia;

  @Column(nullable = false)
  private String razaoSocial;

  @Column(length = 14, nullable = false)
  private String cnpj;

  @Column(length = 50, nullable = false)
  private String publicToken;

  @Column(nullable = false)
  private Boolean ativo = true;

  @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "produtora")
  private List<Evento> eventos;

  @OneToOne(cascade = CascadeType.PERSIST)
  private Usuario usuario;

  public Produtora(String nomeFantasia, String razaoSocial,
                   String cnpj, String publicToken, Usuario usuario) {
    this.nomeFantasia = nomeFantasia;
    this.razaoSocial = razaoSocial;
    this.cnpj = cnpj;
    this.publicToken = publicToken;
    this.usuario = usuario;
  }

  /************************* POSSUI EVENTOS EM ABERTO *************************/

  public boolean possuiEventosEmAberto() {

    return !this.getEventos().stream().allMatch(Evento::jaAcabou);
  }
}
