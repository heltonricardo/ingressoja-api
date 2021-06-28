package io.github.heltonricardo.ingressoja.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause="ativo")
@SQLDelete(sql = "UPDATE evento SET ativo = false WHERE id = ?")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String imagemURL;
	private LocalDateTime inicio;
	private LocalDateTime termino;
	private String descricao;
	private Boolean ativo = true;
		
	private Boolean online;
	private String url;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	
	@ManyToOne
	@NotNull
	private Organizadora organizadora;
	
	@OneToMany
	private List<TipoDeIngresso> tiposDeIngresso;
}
