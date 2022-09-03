package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.controleVendas.vendas.enums.PerfilEnum;

public class ClienteDto {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String criadoEm;
	private String atualizadoEm;
	private String deletadoEm;
	private PerfilEnum perfil;
	
	public ClienteDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "Sobrenome deve conter entre 3 e 200 caracteres.")
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres")
	@Email(message = "Email inválido")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
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

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "ClienteDto [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", senha="
				+ senha + ", criadoEm=" + criadoEm + ", atualizadoEm=" + atualizadoEm + ", deletadoEm=" + deletadoEm
				+ ", perfil=" + perfil + "]";
	}
}
