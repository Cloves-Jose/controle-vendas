package br.com.controleVendas.vendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:deletadoEm WHERE e.id =:produto_id AND e.empresa.id =:empresa_id")
	void deletarEstoque(
			@Param("deletadoEm") String deletadoEm, 
			@Param("empresa_id") Long empresa_id, 
			@Param("produto_id") Long produto_id);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:deletadoEm WHERE e.id =:produto_id AND e.empresa.id =:empresa_id")
	void recuperarEstoque(
			@Param("deletadoEm") String deletadoEm,
			@Param("empresa_id") Long empresa_id,
			@Param("produto_id") Long produto_id);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.marca =:marca AND e.empresa.id =:empresa_id AND e.deletadoEm = null")
	Page<Estoque> findByMarca(
			@Param("marca")String marca, 
			@Param("empresa_id") Long empresa_id, 
			Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.nome =:nome AND e.empresa.id =:empresa_id AND e.deletadoEm = null")
	Page<Estoque> findByNome(
			@Param("nome")String nome, 
			@Param("empresa_id") Long empresa_id, 
			Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.tipo =:tipoProduto AND e.empresa.id =:empresa_id AND e.deletadoEm = null")
	Page<Estoque> findByTipoProduto(
			@Param("tipoProduto") TipoProdutoEnum tipoProduto,
			@Param("empresa_id") Long empresa_id,
			Pageable pageable);
	
}
