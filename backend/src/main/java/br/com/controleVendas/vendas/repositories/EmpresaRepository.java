package br.com.controleVendas.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Empresa;

/**
 * 
 * @author Cloves José
 *
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	@Transactional(readOnly = true)
	Empresa findByEmail(@Param("email") String email);
	
	@Transactional(readOnly = true)
	Empresa findByCnpj(@Param("cnpj") String cnpj);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Empresa e SET e.deletadoEm =:data WHERE e.id =:id")
	void deleteCliente(@Param("id")Long id, @Param("data")String data);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Empresa e SET e.deletadoEm =:valor WHERE e.cnpj =:cnpj")
	void recuperarCliente(@Param("cnpj")String cnpj, @Param("valor") String valor);
}
