package br.com.controleVendas.vendas.services;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Persiste o funcionario na base de dados
	 * 
	 * @param funcionario
	 * @return
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Desativa o funcionário na base de dados
	 * 
	 * @param id
	 * @param data
	 */
	void deletar(Long id, String data);
}
