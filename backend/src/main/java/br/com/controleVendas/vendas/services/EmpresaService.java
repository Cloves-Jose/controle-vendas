package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Empresa;


public interface EmpresaService {
	
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
	Optional<Empresa> listarPorId(Long id);
	
	/**
	 * Retorna um cliente pelo id
	 * 
	 * @param id
	 * @return CadastroPJ
	 */
	Optional<Empresa> listarPorCnpj(String cnpj);
	
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
