package br.com.controleVendas.vendas.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;
import br.com.controleVendas.vendas.services.EstoqueServices;

public class EstoqueServiceImpl implements EstoqueServices{

	@Override
	public Page<Estoque> buscarPorMarca(String marca, Long empresa_id, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Estoque> buscarPorNome(String nome, Long empresa_id, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Estoque> buscarPorTipoProduto(TipoProdutoEnum tipoProduto, Long empresa_id, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
