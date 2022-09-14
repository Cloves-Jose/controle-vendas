package br.com.controleVendas.vendas.repositories;

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
	Estoque findByNome(@Param("nome") String nome);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:data WHERE e.id =:id")
	void deleteProduto(@Param("id")Long id, @Param("data")String data);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Estoque e SET e.deletadoEm =:valor WHERE e.id =:id")
	void recuperarProduto(@Param("id")Long id, @Param("valor") String valor);
	
}
