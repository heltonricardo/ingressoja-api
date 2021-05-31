package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;

@Entity
public class IngressoDisponivel {

	private int id;
	private double valor;
	private int quantidade;
	private String descricao;
}
