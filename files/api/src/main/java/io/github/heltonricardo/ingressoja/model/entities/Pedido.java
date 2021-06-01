package io.github.heltonricardo.ingressoja.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Pedido {

	private Long id;
	private Date data;
	
	private List<ItemPedido> itensPedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}
}
