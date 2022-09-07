package br.com.controleVendas.vendas.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.controleVendas.vendas.enums.PerfilEnum;


@Entity
@Table(name = "empresa")
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String razao_social;
	private String nome_fantasia;
	private String email;
	private String senha;
	private String cnpj;
	private String criadoEm;
	private String atualizadoEm;
	private String deletadoEm;
	private PerfilEnum perfil;
	
	private List<Funcionario> funcionario;

	public Empresa () {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "razao_social", nullable = false)
	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	
	@Column(name = "nome_fantasia", nullable = false)
	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
	
	@Column(name = "cnpj", nullable = false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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
	
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id")
	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "CadastroPJ [id=" + id + ", razao_social=" + razao_social + ", nome_fantasia=" + nome_fantasia
				+ ", email=" + email + ", senha=" + senha + ", cnpj=" + cnpj + ", criadoEm=" + criadoEm
				+ ", atualizadoEm=" + atualizadoEm + ", deletadoEm=" + deletadoEm + ", perfil=" + perfil + "]";
	}
}
