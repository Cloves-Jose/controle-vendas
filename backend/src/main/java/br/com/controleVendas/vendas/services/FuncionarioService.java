package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Persistir um funcionario na base de dados
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Desativar um funcionario na base de dados
	 * 
	 * @param id
	 */
	void deletar(String data, Long id);
	
	/**
	 * Buscar funcionario pelo número do pis
	 * 
	 * @param pis
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorPis(String pis);
	
	/**
	 * Buscar funcionário pelo número do cpf
	 * 
	 * @param cpf
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * 
	 * @param cargo
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorCargo(String cargo);
	
	/**
	 * Ativa o funcionário na base de dados.
	 * 
	 * @param cpf
	 */
	void ativar(String cpf);

}
