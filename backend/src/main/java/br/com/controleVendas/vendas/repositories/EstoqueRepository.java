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
	@Query("SELECT e FROM Estoque e WHERE e.nome =:nome")
	Optional<Estoque> findByNome(@Param("nome") String nome);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM Estoque e WHERE e.fabricante =:fabricante")
	Page<Estoque> findByMarca(
			@Param("fabricante")String fabricante, 
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
