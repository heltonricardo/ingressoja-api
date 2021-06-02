package io.github.heltonricardo.ingressoja.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String senha;
	
	@OneToMany
	private List<Administrador> administradores;
	
	@OneToMany
	private List<Comprador> compradores;
	
	@OneToMany
	private List<Organizadora> organizadoras;
}
