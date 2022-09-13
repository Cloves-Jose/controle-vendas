package br.com.controleVendas.vendas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.controleVendas.vendas.enums.TipoProdutoEnum;

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String marca;
	private String validade;
	private String cadastradoEm;
	private String atualizadoEm;
	private String deletadoEm;
	private int quantidade;
	private double preco;
	private TipoProdutoEnum tipo;
	private Empresa empresa;
	
	public Estoque() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome_produto", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "marca_produto", nullable = false)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column(name = "data_validade", nullable = false)
	public String getValidade() {
		return validade;
	}
	
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	@Column(name = "cadastradoEm", nullable = false)
	public String getCadastradoEm() {
		return cadastradoEm;
	}

	public void setCadastradoEm(String cadastradoEm) {
		this.cadastradoEm = cadastradoEm;
	}
	
	@Column(name = "atualizadoEm", nullable = true)
	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	@Column(name = "deletadoEm", nullable = true)
	public String getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(String deletadoEm) {
		this.deletadoEm = deletadoEm;
	}
	
	@Column(name = "quantidade", nullable = false)
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Column(name = "preco", nullable = false)
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Column(name = "tipo_produto", nullable = false)
	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Estoque [id=" + id + ", nome=" + nome + ", marca=" + marca + ", validade=" + validade
				+ ", cadastradoEm=" + cadastradoEm + ", atualizadoEm=" + atualizadoEm + ", deletadoEm=" + deletadoEm
				+ ", quantidade=" + quantidade + ", preco=" + preco + ", tipo=" + tipo + ", empresa=" + empresa + "]";
	}
}
