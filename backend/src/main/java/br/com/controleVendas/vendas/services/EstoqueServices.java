package br.com.controleVendas.vendas.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;

public interface EstoqueServices {
	
	/**
	 * Realiza a consulta por marca. Consulta paginada
	 * 
	 * @param marca
	 * @param empresa_id
	 * @param pageRequest
	 * @return Estoque
	 */
	Page<Estoque> buscarPorMarca(String marca, Long empresa_id, PageRequest pageRequest);
	
	/**
	 * Realiza a consulta por nome. Consulta paginada
	 * 
	 * @param nome
	 * @param empresa_id
	 * @param pageRequest
	 * @return Estoque
	 */
	Page<Estoque> buscarPorNome(String nome, Long empresa_id, PageRequest pageRequest);
	
	/**
	 * Realiza a consulta por Tipo do Produto. Consulta paginada.
	 * 
	 * @param tipoProduto
	 * @param empresa_id
	 * @param pageRequest
	 * @return Estoque.
	 */
	Page<Estoque> buscarPorTipoProduto(TipoProdutoEnum tipoProduto, Long empresa_id, PageRequest pageRequest);
	
}
