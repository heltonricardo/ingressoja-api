package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Where(clause="ativo")
@SQLDelete(sql = "UPDATE categoria_evento SET ativo = false WHERE id = ?")
public class CategoriaEvento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String nome;
	
	private Boolean ativo = true;
	
	@OneToMany
	private List<Evento> eventos;
}
