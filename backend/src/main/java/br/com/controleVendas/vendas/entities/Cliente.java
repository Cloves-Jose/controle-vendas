package br.com.controleVendas.vendas.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.controleVendas.vendas.enums.PerfilEnum;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private Date criadoEm;
	private Date atualizadoEm;
	private Date deletadoEm;
	private PerfilEnum perfil;
	
	public Cliente () {}
	
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
	public Date getCriadoEm() {
		return criadoEm;
	}
	
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}
	
	@Column(name = "atualizadoEm", nullable = true)
	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	@Column(name = "deletadoEm", nullable = true)
	public Date getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(Date deletadoEm) {
		this.deletadoEm = deletadoEm;
	}
	
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", senha="
				+ senha + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + ", deletadoEm=" + deletadoEm
				+ ", perfil=" + perfil + "]";
	}
}
