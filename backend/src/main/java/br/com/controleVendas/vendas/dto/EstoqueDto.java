package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.NotEmpty;

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
	private String marca;
	private String validade;
	private String cadastradoEm;
	private String atualizadoEm;
	private String deletadoEm;
	private int quantidade;
	private double preco;
	private TipoProdutoEnum tipo;
	private String cnpj;
	
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
	
	@NotEmpty(message = "Marca não pode ser vazio.")
	@Length(min = 3, max = 200, message = "A marca deve conter entre 3 e 200 caracteres")
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@NotEmpty(message = "Validade não pode ser vazio.")
	@Length(min = 10, max = 10, message = "Validade deve conter 10 caracteres")
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
	
	@NonNull
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@NonNull
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		if(preco < 0) {
			log.info("O preço deve ser maior que zero");
		}
		this.preco = preco;
	}
	
	
	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
		this.tipo = tipo;
	}
	
	@NotEmpty(message = "CNPJ não pode ser vazio")
	@CNPJ(message = "CNPJ deve ser preenchido corretamente.")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
