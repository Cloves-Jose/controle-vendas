package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@Transactional(readOnly = true)
	Cliente findByNomeStartsWith(String nome);
	
	@Query("UPDATE c FROM Cliente c WHERE c.deletadoEm=:data AND c.id=:id")
	void deleteCliente(Long id, String data);

}
