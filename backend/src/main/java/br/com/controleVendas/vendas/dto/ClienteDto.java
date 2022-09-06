package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.controleVendas.vendas.enums.PerfilEnum;
/**
 * 
 * @author Cloves José
 *
 */
public class ClienteDto {
	
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
	
	public ClienteDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@NotEmpty(message = "Razão social não pode ser vazia")
	@Length(min = 3, max = 200, message = "Razão social deve conter entre 3 e 200 caracteres.")
	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	
	
	@NotEmpty(message = "Nome fantasia não pode ser vazia")
	@Length(min = 3, max = 200, message = "Nome fantasia deve conter entre 3 e 200 caracteres.")
	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	
	@NotEmpty(message = "CNPJ não pode ser vazio")
	@Length(min = 14, max = 14, message = "CNPJ deve conter exatamente 14 caracteres.")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
		return "ClienteDto [id=" + id + ", razao_social=" + razao_social + ", nome_fantasia=" + nome_fantasia
				+ ", email=" + email + ", senha=" + senha + ", cnpj=" + cnpj + ", criadoEm=" + criadoEm
				+ ", atualizadoEm=" + atualizadoEm + ", deletadoEm=" + deletadoEm + ", perfil=" + perfil + "]";
	}

	
}
