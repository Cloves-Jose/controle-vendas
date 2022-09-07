package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.CadastroPJ;

/**
 * 
 * @author Cloves José
 *
 */
public interface CadastroPJService {
	
	/**
	 * Retorna um cliente pelo email somente se ele estiver ativo
	 * 
	 * @param email
	 * @return CadastroPJ
	 */
	Optional<CadastroPJ> buscarPorEmail(String email);
	
	/**
	 * Verifica se o email já está cadastrado
	 * 
	 * @param email
	 * @return CadastroPJ
	 */
	Optional<CadastroPJ> listarPorEmail(String email);
	
	/**
	 * Busca um cliente pelo ID
	 * 
	 * @param id
	 * @return CadastroPJ
	 */
	Optional<CadastroPJ> buscarPorId(Long id);
	
	/**
	 * Retorna um cliente pelo id
	 * 
	 * @param id
	 * @return CadastroPJ
	 */
	Optional<CadastroPJ> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra um novo cliente na base de dados
	 * 
	 * @param cliente
	 * @return CadastroPJ
	 */
	CadastroPJ persistir(CadastroPJ cliente);
	
	/**
	 * Realiza o soft-delete de um cliente na base de dados
	 * 
	 * @param id
	 * @param data
	 */
	void deletar(Long id, String data);
	
	/**
	 * Recupera CadastroPJ deletado
	 * 
	 * @param cnpj
	 * @return CadastroPJ
	 */
	void recuperarDeletado(String cnpj);

}
