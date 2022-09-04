package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Cliente;

/**
 * 
 * @author Cloves José
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(@Param("email") String email);
	
	@Transactional(readOnly = true)
	Page<Cliente> findByNomeStartsWith(@Param("nome")String nome, 
			@Param("pageRequest")PageRequest pageRequest);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT c FROM Cliente c WHERE c.id =:clienteId")
	Cliente findByClienteId(@Param("clienteId") Long clienteId);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Cliente c SET c.deletadoEm =:data WHERE c.id =:id")
	int deleteCliente(@Param("id")Long id, @Param("data")String data);

}
