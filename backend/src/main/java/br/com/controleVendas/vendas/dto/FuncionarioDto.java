package br.com.controleVendas.vendas.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.controleVendas.vendas.enums.PerfilEnum;

public class FuncionarioDto {
	
	private Long id;
	private String nome;
	private String sobrenome;
	private String data_nascimento;
	private String numero_residencia;
	private String cpf;
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
	@Length(min = 8, max = 8, message = "A data de nascimento deve conter 8 caracteres")
	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getNumero_residencia() {
		return numero_residencia;
	}

	public void setNumero_residencia(String numero_residencia) {
		this.numero_residencia = numero_residencia;
	}

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

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

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
	
	

}
