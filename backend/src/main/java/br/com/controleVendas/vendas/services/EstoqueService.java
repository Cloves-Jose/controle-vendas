package br.com.controleVendas.vendas.services;

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
	 * Buscar produto pelo nome
	 * 
	 * @param nome
	 * @return
	 */
	Estoque buscarPorNome(String nome);
}
