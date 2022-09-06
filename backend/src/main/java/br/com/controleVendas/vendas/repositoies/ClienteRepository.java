package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.CadastroPJ;

/**
 * 
 * @author Cloves José
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<CadastroPJ, Long>{
	
	@Transactional(readOnly = true)
	CadastroPJ findByEmail(@Param("email") String email);
	
	
	@Transactional(readOnly = true)
	@Query("SELECT c FROM Cliente c WHERE c.id =:clienteId")
	CadastroPJ findByClienteId(@Param("clienteId") Long clienteId);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Cliente c SET c.deletadoEm =:data WHERE c.id =:id")
	int deleteCliente(@Param("id")Long id, @Param("data")String data);

}
