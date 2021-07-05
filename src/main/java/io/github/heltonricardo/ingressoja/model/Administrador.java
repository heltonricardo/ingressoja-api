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

	private String nome;
	private String email;
	private Boolean ativo = true;

	@ManyToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL)
	List<Saque> saques;
	
	public void addSaque(Saque saque) {
		saques.add(saque);
	}

	public Administrador(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
}
