package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.lang.NonNull;

import br.com.controleVendas.vendas.enums.TipoProdutoEnum;

public class EstoqueDto {
	
	private static Logger log =  LoggerFactory.getLogger(EstoqueDto.class);
	
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
	
	public EstoqueDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "O nome deve conter entre 3 e 200 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public String getCadastradoEm() {
		return cadastradoEm;
	}

	public void setCadastradoEm(String cadastradoEm) {
		this.cadastradoEm = cadastradoEm;
	}

	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public String getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(String deletadoEm) {
		this.deletadoEm = deletadoEm;
	}
	
	@NotEmpty(message = "Fabricante não pode ser vazio")
	@Length(min = 1, max = 100, message = "Fabicante deve ter entre 3 e 100 caracteres.")
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	
	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public String getDimencoes() {
		return dimencoes;
	}

	public void setDimencoes(String dimencoes) {
		this.dimencoes = dimencoes;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}
	
	
	
	
}
