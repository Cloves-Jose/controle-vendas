package br.com.controleVendas.vendas.services;

import br.com.controleVendas.vendas.entities.EmpresaEstoque;

public interface EmpresaEstoqueService {
	
	/**
	 * Vincula um produto do estoque a uma determinada empresa
	 * 
	 * @param empresaEstoqueService
	 * @return EmpresaEstoqueService
	 */
	EmpresaEstoque persistir(EmpresaEstoque empresaEstoque);
}
