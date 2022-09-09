package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import br.com.controleVendas.vendas.enums.PerfilEnum;

public class FuncionarioDto {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String data_nascimento;
	private String numero_residencia;
	private String cpf;
	private String rua;
	private String email;
	private String senha;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String atualizadoEm;
	private String criadoEm;
	private String deletadoEm;
	private String pis;
	private String cargo;
	private String cnpj;
	private PerfilEnum perfil;
	
	public FuncionarioDto() {}

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
	
	@NotEmpty(message = "Sobrenome não pode ser vazio")
	@Length(min = 3, max = 200, message = "O sobrenome deve conter entre 3 e 200 caracteres")
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	@NotEmpty(message = "Data de nascimento obrigatória")
	@Length(min = 10, max = 10, message = "A data de nascimento deve conter 10 caracteres")
	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	@NotEmpty(message = "Número residencial não pode ser vazio")
	@Length(min = 1, max = 5, message = "O número residencial deve conter entre 1 e 5 caracteres")
	public String getNumero_residencia() {
		return numero_residencia;
	}

	public void setNumero_residencia(String numero_residencia) {
		this.numero_residencia = numero_residencia;
	}
	
	@NotEmpty(message = "CPF não pode ser vazio")
	@CPF(message = "O CPF não pode ser vazio.")
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@NotEmpty(message = "CEP deve ser preenchido")
	@Length(min = 8, max = 8)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(String atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public String getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(String criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getDeletadoEm() {
		return deletadoEm;
	}

	public void setDeletadoEm(String deletadoEm) {
		this.deletadoEm = deletadoEm;
	}
	
	@NotEmpty(message = "PIS deve ser informado")
	@Length(min = 10, max = 11, message = "PIS deve ser informado.")
	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}
	
	@NotEmpty(message = "Cargo deve ser informado.")
	@Length(min = 3, max = 20, message = "Cargo deve ser informado.")
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}
	
	@NotEmpty(message = "CNPJ deve ser informado")
	@CNPJ(message = "CNPJ deve ser válido")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@NotEmpty(message = "Email deve ser informado")
	@Email(message = "Email deve ser informado.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty(message = "Senha deve ser informada")
	@Length(min = 3, max = 200, message = "A senha deve ter entre 3 e 200 caracteres.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
