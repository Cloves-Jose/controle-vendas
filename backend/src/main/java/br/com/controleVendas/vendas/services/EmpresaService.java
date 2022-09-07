package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Empresa;

/**
 * 
 * @author Cloves José
 *
 */
public interface EmpresaService {
	
	/**
	 * Retorna um cliente pelo email somente se ele estiver ativo
	 * 
	 * @param email
	 * @return CadastroPJ
	 */
	Optional<Empresa> buscarPorEmail(String email);
	
	/**
	 * Verifica se o email já está cadastrado
	 * 
	 * @param email
	 * @return CadastroPJ
	 */
	Optional<Empresa> listarPorEmail(String email);
	
	/**
	 * Busca um cliente pelo ID
	 * 
	 * @param id
	 * @return CadastroPJ
	 */
	Optional<Empresa> buscarPorId(Long id);
	
	/**
	 * Retorna um cliente pelo id
	 * 
	 * @param id
	 * @return CadastroPJ
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra um novo cliente na base de dados
	 * 
	 * @param cliente
	 * @return CadastroPJ
	 */
	Empresa persistir(Empresa cliente);
	
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
