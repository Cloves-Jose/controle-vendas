package br.com.controleVendas.vendas.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.persistence.Id;

@Entity
@Table(name = "empresaEstoque")
public class EmpresaEstoque implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Empresa empresa;
	private Estoque estoque;
	private int quantidade;
	private double preco;
	private String criadoEm;
	private String atualizadoEm;
	private String deletadoEm;
	
	public EmpresaEstoque() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
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
	
	@Column(name = "criadoEm", nullable = true)
	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
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

	@Override
	public String toString() {
		return "EmpresaEstoque [id=" + id + ", empresa=" + empresa + ", estoque=" + estoque + ", quantidade="
				+ quantidade + ", preco=" + preco + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm
				+ ", deletadoEm=" + deletadoEm + "]";
	}
	
	
	
	

}
