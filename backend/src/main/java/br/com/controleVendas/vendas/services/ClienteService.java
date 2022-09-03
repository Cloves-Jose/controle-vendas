package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Cliente;

public interface ClienteService {
	
	/**
	 * Retorna um cliente pelo email
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorEmail(String email);
	
	
	/**
	 * Retorna um cliente pelo id
	 * 
	 * @param id
	 * @return Optinal<Cliente>
	 */
	Optional<Cliente> buscarPorId(String id);
	
	
	/**
	 * Retorna um cliente pelo nome
	 * 
	 * @param nome
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorNome(String nome);
	
	/**
	 * Cadastra um novo cliente na base de dados
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	Cliente persistir(Cliente cliente);
	
	/**
	 * Realiza o soft-delete de um cliente na base de dados
	 * @param id
	 */
	void deletar(Long id);
	
	/**
	 * Atualiza os dados do cliente
	 * 
	 * @param id
	 * @return Cliente
	 */
	Cliente atualizar(Long id);

}
