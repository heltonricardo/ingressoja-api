package io.github.heltonricardo.ingressoja.model.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Saque {

	private Long id;
	private String horario;
	private Date data;
	private Double valor;
}
