package br.com.controleVendas.vendas.repositories;

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
public interface CadastroPJRepository extends JpaRepository<CadastroPJ, Long>{
	
	@Transactional(readOnly = true)
	CadastroPJ findByEmail(@Param("email") String email);
	
	@Transactional(readOnly = true)
	CadastroPJ findByCnpj(@Param("cnpj") String cnpj);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE CadastroPJ c SET c.deletadoEm =:data WHERE c.id =:id")
	void deleteCliente(@Param("id")Long id, @Param("data")String data);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE CadastroPJ c SET c.deletadoEm =:valor WHERE c.cnpj =:cnpj")
	void recuperarCliente(@Param("cnpj")String cnpj, @Param("valor") String valor);
}
