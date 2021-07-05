package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
