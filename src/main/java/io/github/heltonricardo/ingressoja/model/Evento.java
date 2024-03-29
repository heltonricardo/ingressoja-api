package io.github.heltonricardo.ingressoja.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.heltonricardo.ingressoja.utils.Variados.hojeEstaEntre;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "evento")
@SQLDelete(sql = "UPDATE evento SET ativo = false WHERE id = ?")
public class Evento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String titulo;

  @Column(length = 1000, nullable = false)
  private String imagemURL = "";

  @Column(nullable = false)
  private Date inicio;

  @Column(nullable = false)
  private Date termino;

  @Column(length = 2000, nullable = false)
  private String descricao;

  @Column(nullable = false)
  private Boolean online;

  @Column(length = 1000)
  private String url;

  @Column(length = 100)
  private String logradouro;

  @Column(length = 10)
  private String numero;

  @Column(length = 50)
  private String bairro;

  @Column(length = 50)
  private String cidade;

  @Column(length = 2)
  private String uf;

  @Column(length = 8)
  private String cep;

  @Column(nullable = false)
  private Boolean ativo = true;

  @Column(nullable = false)
  private Integer totalIngressos;

  @Column(nullable = false)
  private Boolean vendaPausada = false;

  @Column(nullable = false)
  private Integer qntTotalVendas = 0;

  @Column(nullable = false)
  private Integer qntVendasProcessadas = 0;

  @Column(nullable = false)
  private Integer qntVendasPendentes = 0;

  @Column(nullable = false)
  private Integer qntVendasCanceladasPgto = 0;

  @Column(nullable = false)
  private Integer qntVendasCanceladasSolic = 0;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Produtora produtora;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private CategoriaEvento categoriaEvento;

  @OneToMany(mappedBy = "evento",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TipoDeIngresso> tiposDeIngresso;

  @OneToMany(mappedBy = "evento", cascade = CascadeType.PERSIST)
  private List<Despesa> despesas;

  @OneToMany(mappedBy = "evento")
  private List<Pedido> pedidos;

  @Transient
  private Long idProdutora;

  @Transient
  private Long idCategoria;

  public Evento(String titulo, Date inicio, Date termino, String descricao,
                Boolean online, String url, String logradouro, String numero,
                String bairro, String cidade, String uf, String cep,
                Integer totalIngressos, List<TipoDeIngresso> tiposDeIngresso,
                Long idProdutora, Long idCategoria) {
    this.titulo = titulo;
    this.inicio = inicio;
    this.termino = termino;
    this.descricao = descricao;
    this.online = online;
    this.url = url;
    this.logradouro = logradouro;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.uf = uf;
    this.cep = cep;
    this.totalIngressos = totalIngressos;
    this.tiposDeIngresso = tiposDeIngresso;
    this.idProdutora = idProdutora;
    this.idCategoria = idCategoria;
    this.despesas = new ArrayList<>();
  }

  /************************ POSSUI INGRESSOS VENDIDOS? ************************/

  public boolean possuiIngressosVendidos() {

    return this.getTiposDeIngresso().stream()
        .anyMatch(t -> t.getQuantidadeDisponivel().intValue()
            != t.getQuantidadeTotal().intValue());
  }

  /************************** GET INGRESSOS VÁLIDOS ***************************/

  public List<TipoDeIngresso> getIngressosValidos() {

    return this.getTiposDeIngresso().stream()
        .filter(t -> hojeEstaEntre(t.getInicio(), t.getTermino())
            && t.getQuantidadeDisponivel() > 0)
        .collect(Collectors.toList());
  }

  /********************** INGRESSOS NÃO ESTÃO CONFORME? ***********************/

  public boolean ingressosNaoConforme() {

    int maxGratis = (int) (this.getTotalIngressos() * 0.1);

    boolean gratisConforme =
        maxGratis >= this.getTiposDeIngresso().stream()
            .filter(t -> t.getValor() == 0.0)
            .reduce(0, (s, t) ->s + t.getQuantidadeTotal(), Integer::sum);

    boolean totalIngressosConforme =
        this.getTotalIngressos().equals(this.getTiposDeIngresso().stream()
            .reduce(0, (s, t) ->
                s + t.getQuantidadeTotal(), Integer::sum));

    return !(gratisConforme && totalIngressosConforme);
  }

  /**************** CALCULAR QUANTIDADE DE INGRESSOS VENDIDOS *****************/

  public Integer calcularQntIngressosVendidos() {

    return this.getTiposDeIngresso().stream().reduce(0,
        (s, t) -> s + t.calcularQntVendida(), Integer::sum);
  }

  /**************** CALCULAR PORCENTAGEM DE INGRESSOS VENDIDOS ****************/

  public Double calcularPorcentagemIngressosVendidos() {

    return
        this.calcularQntIngressosVendidos() * 100. / this.getTotalIngressos();
  }

  /************************** CALCULAR RECEITA BRUTA **************************/

  public Double calcularReceitaBruta() {

    return this.getTiposDeIngresso()
        .stream()
        .reduce(0.0, (acc, curr) -> acc + curr.calcularReceitaGerada(),
            Double::sum);
  }

  /**************************** ADICIONAR DESPESA *****************************/

  public void adicionarDespesa(Despesa despesa) {

    this.despesas.add(despesa);
  }

  /************************* CALCULAR TOTAL DESPESAS **************************/
  public Double calcularTotalDespesas() {

    return this.getDespesas().stream().reduce(.0,
        (acc, curr) -> acc + curr.getValor(), Double::sum);
  }

  /************************* CALCULAR RECEITA LÍQUIDA *************************/

  public Double calcularReceitaLiquida() {

    return this.calcularReceitaBruta() - this.calcularTotalDespesas();
  }

  /*********************** CONTROLE DO NÚMERO DE VENDAS ***********************/

  public void aumentarTotalVendas() {
    ++this.qntTotalVendas;
  }

  public void aumentarVendasProcessadas() {
    ++this.qntVendasProcessadas;
  }

  public void diminuirVendasProcessadas() {
    --this.qntVendasProcessadas;
  }

  public void aumentarVendasPendentes() {
    ++this.qntVendasPendentes;
  }

  public void diminuirVendasPendentes() {
    --this.qntVendasPendentes;
  }

  public void aumentarVendasCanceladasPgto() {
    ++this.qntVendasCanceladasPgto;
  }

  public void aumentarVendasCanceladasSolic() {
    ++this.qntVendasCanceladasSolic;
  }

  /***************************** EVENTO EXCLUÍDO? *****************************/

  public boolean isExcluido() {

    return !this.getAtivo();
  }

  /**************************** EVENTO JÁ ACABOU? *****************************/

  public boolean jaAcabou() {

    return isExcluido() || (new Date()).compareTo(this.termino) > 0;
  }

  /**************************** VENDAS ESGOTADAS? *****************************/

  public boolean isIngressosEsgotados() {

    return this.getTiposDeIngresso().stream()
        .allMatch(TipoDeIngresso::isEsgotado);
  }
}
