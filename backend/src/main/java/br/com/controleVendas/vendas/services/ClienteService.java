package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Cliente;

/**
 * 
 * @author Cloves José
 *
 */
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
	Optional<Cliente> buscarPorId(Long clienteId);
	
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
	int deletar(Long id, String data);

}
