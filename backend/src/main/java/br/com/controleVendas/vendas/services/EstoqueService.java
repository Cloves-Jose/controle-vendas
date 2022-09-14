package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Estoque;

public interface EstoqueService {
	
	/**
	 * Persistir o estoque na base de dados
	 * 
	 * @param estoque
	 * @return Estoque
	 */
	Estoque persistir(Estoque estoque);
	
	/**
	 * Buscar produto, desativado ou não, pelo nome
	 * 
	 * @param nome
	 * @return
	 */
	Optional<Estoque> buscarPorNome(String nome, Long empresa_id);
}
