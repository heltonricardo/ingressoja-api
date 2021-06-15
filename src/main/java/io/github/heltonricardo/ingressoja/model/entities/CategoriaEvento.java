package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DynamicUpdate
@Where(clause = "ativo")
public class CategoriaEvento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;

	private Boolean ativo = true;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Evento> eventos;
	
	public void addEvento(Evento evento) {
		eventos.add(evento);
	}
}
