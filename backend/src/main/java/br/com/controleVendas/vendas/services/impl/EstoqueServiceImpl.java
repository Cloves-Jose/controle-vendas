package br.com.controleVendas.vendas.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;
import br.com.controleVendas.vendas.repositories.EstoqueRepository;
import br.com.controleVendas.vendas.services.EstoqueServices;

@Service
public class EstoqueServiceImpl implements EstoqueServices{
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	private static Logger log = LoggerFactory.getLogger(EstoqueServiceImpl.class);

	@Override
	public Page<Estoque> buscarPorMarca(String marca, Long empresa_id, PageRequest pageRequest) {
		Page<Estoque> consulta = estoqueRepository.findByMarca(marca, empresa_id, pageRequest);
		
		if(consulta.isEmpty()) {
			log.info("Nenhum registro encontrado");
			return Page.empty();
		}
		log.info("Buscando por produtos na base de dados.");
		return consulta;
	}

	@Override
	public Page<Estoque> buscarPorNome(String nome, Long empresa_id, PageRequest pageRequest) {
		Page<Estoque> consulta = estoqueRepository.findByNome(nome, empresa_id, pageRequest);
		
		if(consulta.isEmpty()) {
			log.info("Nenhum registro encontrado");
			return Page.empty();
		}
		log.info("Buscando por produtos na base de dados");
		return consulta;
	}

	@Override
	public Page<Estoque> buscarPorTipoProduto(TipoProdutoEnum tipoProduto, Long empresa_id, PageRequest pageRequest) {
		Page<Estoque> consulta = estoqueRepository.findByTipoProduto(tipoProduto, empresa_id, pageRequest);
		
		if(consulta.isEmpty()) {
			log.info("Nenhum registro encontrado");
			return Page.empty();
		}
		return consulta;
	}

	@Override
	public void ativarEstoque(String deletadoEm, Long empresa_id, Long produto_id) {
		log.info("Ativando produto na base de dados");
		estoqueRepository.recuperarEstoque(deletadoEm, empresa_id, produto_id);
		
	}

	@Override
	public void deletarEstoque(String deletadoEm, Long empresa_id, Long produto_id) {
		log.info("Deletando produto na base de dados");
		estoqueRepository.deletarEstoque(deletadoEm, empresa_id, produto_id);
		
	}

	

}
