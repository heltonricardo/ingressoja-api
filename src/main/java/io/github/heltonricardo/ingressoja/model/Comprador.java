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
	private String nome;
	private String email;
	private String cpf;
	private Boolean ativo = true;

	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@OneToMany
	private List<Pedido> pedidos;

	public Comprador(String nome, String email, String cpf, Usuario usuario) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.usuario = usuario;
	}
}
