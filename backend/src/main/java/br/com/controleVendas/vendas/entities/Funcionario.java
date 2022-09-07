package br.com.controleVendas.vendas.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.controleVendas.vendas.enums.PerfilEnum;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String data_nascimento;
	private String idade;
	private String numero_residencia;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String atualizadoEm;
	private String criadoEm;
	private String deletadoEm;
	private String pis;
	private String cargo;
	private PerfilEnum perfil;
	
	public Funcionario() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "sobrenome", nullable = false)
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@Column(name = "data_nascimento", nullable = false)
	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	@Column(name = "idade", nullable = false)
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	@Column(name = "numero_residencia", nullable = false)
	public String getNumero_residencia() {
		return numero_residencia;
	}

	public void setNumero_residencia(String numero_residencia) {
		this.numero_residencia = numero_residencia;
	}
	
	@Column(name = "rua", nullable = false)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
	
	@Column(name = "bairro", nullable = false)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	@Column(name = "cidade", nullable = false)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Column(name = "estado", nullable = false)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name = "cep", nullable = false)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Column(name = "atualizado_em", nullable = true)
	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	@Column(name = "criado_em", nullable = true)
	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	@Column(name = "deletado_em", nullable = true)
	public String getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(String deletadoEm) {
		this.deletadoEm = deletadoEm;
	}
	
	@Column(name = "pis", nullable = false)
	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}
	
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@Column(name = "cargo", nullable = false)
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
}