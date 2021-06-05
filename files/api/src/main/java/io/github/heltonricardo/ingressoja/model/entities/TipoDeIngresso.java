package io.github.heltonricardo.ingressoja.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Where(clause="ativo")
@SQLDelete(sql = "UPDATE tipo_de_ingresso SET ativo = false WHERE id = ?")
public class TipoDeIngresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double valor;
	private String nome;
	private String descricao;
	private Integer quantidade;
	private Boolean ativo = true;
}
