package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String email;
	private String ingressante;

	@ManyToOne
	private TipoDeIngresso tipoDeIngresso;

	public ItemPedido(String cpf, String email, String ingressante,
			TipoDeIngresso tipoDeIngresso) {
		this.cpf = cpf;
		this.email = email;
		this.ingressante = ingressante;
		this.tipoDeIngresso = tipoDeIngresso;
	}
}
