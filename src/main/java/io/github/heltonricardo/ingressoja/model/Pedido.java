package io.github.heltonricardo.ingressoja.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataHora;
	private String numeroCartao;
	private String codigoSegurancaCartao;
	private String nomeCartao;
	private String cpfCartao;
	private Double valorTotal;

	@OneToMany
	private List<ItemPedido> itensPedido;

	public Pedido(LocalDateTime dataHora, String numeroCartao,
			String codigoSegurancaCartao, String nomeCartao, String cpfCartao) {
		this.dataHora = dataHora;
		this.numeroCartao = numeroCartao;
		this.codigoSegurancaCartao = codigoSegurancaCartao;
		this.nomeCartao = nomeCartao;
		this.cpfCartao = cpfCartao;
	}
}
