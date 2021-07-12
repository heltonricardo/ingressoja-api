package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause="ativo")
public class TipoDeIngresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Double valor;
	private String descricao;
	private Integer quantidadeTotal;
	private Integer quantidadeDisponivel;
	private Boolean ativo = true;

	@ManyToOne
	private Evento evento;

	public TipoDeIngresso(String nome, Double valor, String descricao,
			Integer quantidadeTotal) {
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.quantidadeTotal = quantidadeTotal;
		this.quantidadeDisponivel = quantidadeTotal;
	}
}
