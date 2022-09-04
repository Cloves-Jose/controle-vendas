package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Funcionario f SET f.deletadoEm =:data WHERE f.id =:id")
	@Transactional(readOnly = false)
	int deleteFuncionario(@Param("id") Long id, @Param("data") String data);
}
