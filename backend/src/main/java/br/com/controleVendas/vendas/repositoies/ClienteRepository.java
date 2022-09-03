package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	Cliente findByNomeStartsWith(String nome);
	
	@Query("UPDATE Cliente c SET c.deletadoEm =:data WHERE c.id =:id")
	void deleteCliente(@Param("id")Long id, @Param("data")String data);

}
