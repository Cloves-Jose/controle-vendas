package br.com.controleVendas.vendas.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String fabricante;
	private String modelo;
	private String validade;
	private String cadastradoEm;
	private String atualizadoEm;
	private String deletadoEm;
	private TipoProdutoEnum tipo;
	private String numeroDeSerie;
	private String dimencoes;

	private String lote;
	
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

	@Column(name = "data_validade", nullable = true)
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
	
	@Column(name = "tipo_produto", nullable = false)
	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}
	
	@Column(name = "fabricante", nullable = false)
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	@Column(name = "modelo", nullable = false)
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Column(name = "numero_serie", nullable = true)
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	@Column(name = "dimencao", nullable = true)
	public String getDimencoes() {
		return dimencoes;
	}

	public void setDimencoes(String dimencoes) {
		this.dimencoes = dimencoes;
	}
	
	@Column(name = "lote", nullable = true)
	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
	
	@Override
	public String toString() {
		return "Estoque [id=" + id + ", nome=" + nome + ", fabricante=" + fabricante + ", modelo=" + modelo
				+ ", validade=" + validade + ", cadastradoEm=" + cadastradoEm + ", atualizadoEm=" + atualizadoEm
				+ ", deletadoEm=" + deletadoEm + ", tipo=" + tipo + ", numeroDeSerie=" + numeroDeSerie + ", dimencoes="
				+ dimencoes + ", lote=" + lote + "]";
	}
	
}
