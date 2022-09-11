package br.com.controleVendas.vendas.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;

public interface EstoqueServices {
	
	/**
	 * Ativa um produto na base de dados.
	 * 
	 * @param deletadoEm
	 * @param empresa_id
	 * @param produto_id
	 */
	void ativarEstoque(String deletadoEm, Long empresa_id, Long produto_id);
	
	/**
	 * Deletar um produto da base de dados.
	 * 
	 * @param deletadoEm
	 * @param empresa_id
	 * @param produto_id
	 */
	void deletarEstoque(String deletadoEm, Long empresa_id, Long produto_id);
	
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
