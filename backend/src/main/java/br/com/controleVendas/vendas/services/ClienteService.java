package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.CadastroPJ;

/**
 * 
 * @author Cloves José
 *
 */
public interface ClienteService {
	
	/**
	 * Retorna um cliente pelo email somente se ele estiver ativo
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<CadastroPJ> buscarPorEmail(String email);
	
	/**
	 * Verifica se o email já está cadastrado
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<CadastroPJ> listarPorEmail(String email);
	
	/**
	 * Retorna um cliente pelo id
	 * 
	 * @param id
	 * @return Optinal<Cliente>
	 */
	Optional<CadastroPJ> buscarPorId(Long clienteId);
	
	/**
	 * Cadastra um novo cliente na base de dados
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	CadastroPJ persistir(CadastroPJ cliente);
	
	/**
	 * Realiza o soft-delete de um cliente na base de dados
	 * @param id
	 */
	int deletar(Long id, String data);

}
