package io.github.heltonricardo.ingressoja.model.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Saque {

	private Long id;
	private String horario;
	private Date data;
	private Double valor;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
