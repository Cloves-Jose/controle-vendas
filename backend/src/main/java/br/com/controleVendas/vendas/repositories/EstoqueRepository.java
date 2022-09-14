package br.com.controleVendas.vendas.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.nome =:nome AND e.empresa.id =:empresa_id")
	Optional<Estoque> findByNome(@Param("nome") String nome, @Param("empresa_id") Long empresa_id);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.marca =:marca AND e.empresa.id =:empresa_id AND e.deletadoEm = null")
	Page<Estoque> findByMarca(
			@Param("marca")String marca, 
			@Param("empresa_id")Long empresa_id, 
			PageRequest pageRequest);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:data WHERE e.id =:id")
	void deleteProduto(@Param("id")Long id, @Param("data")String data);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:valor WHERE e.id =:id")
	void recuperarProduto(@Param("id")Long id, @Param("valor") String valor);
	
}
