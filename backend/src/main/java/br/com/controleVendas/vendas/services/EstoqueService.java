package br.com.controleVendas.vendas.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
	
	/**
	 * Busca paginada de produtos da mesma marca.
	 * 
	 * @param marca
	 * @param empresa_id
	 * @param pageResquest
	 * @return
	 */
	Page<Estoque> buscarPorMarca(String marca, Long empresa_id, PageRequest pageResquest);
	
	/**
	 * Valida a data para armazenar no banco de dados
	 * 
	 * @param data
	 */
	boolean validarData(String data);
}
