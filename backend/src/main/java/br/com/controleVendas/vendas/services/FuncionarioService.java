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
	 * Retorna somente os funcionários ativos na base de dados.
	 * 
	 * @param pis
	 * @return Funcionario
	 */
	Optional<Funcionario> listarPorPis(String pis);
	
	/**
	 * Retorna somente os funcionários ativos na base de dados.
	 * 
	 * @param cpf
	 * @return Funcionario
	 */
	Optional<Funcionario> listarPorCpf(String cpf);
	
	/**
	 * Ativa o funcionário na base de dados.
	 * 
	 * @param cpf
	 */
	void ativar(String cpf);
	
	/**
	 * Retorna funcionário independente dele estar ativo ou não.
	 * 
	 * @param pis
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorPis(String pis);
	
	/**
	 * Retorna funcionário independente dele estar ativo ou não.
	 * 
	 * @param cpf
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * Retorna funcionário independente dele estar ativo ou não.
	 * 
	 * @param email
	 * @return Funcionario
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Retorna funcionário ativo na base de dados.
	 * 
	 * @param id
	 * @return Funcionario
	 */
	Optional<Funcionario> listarPorId(Long id);
	
	/**
	 * Retorna os funcionário vinculados a empresa;
	 * 
	 * @param empresa_id
	 * @return Funcionario
	 */
	Optional<Funcionario> listarAssociados(Long empresa_id);
}
