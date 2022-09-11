package br.com.controleVendas.vendas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Funcionario f SET f.deletadoEm =:data WHERE f.id =:id")
	void deletarFuncionario(@Param("data")String data, 
			@Param("id")Long id);
	
	@Modifying(clearAutomatically = true)
	@Transactional(readOnly = false)
	@Query("UPDATE Funcionario f SET f.deletadoEm =:valor WHERE f.cpf =:cpf")
	void ativarFuncionario(@Param("cpf") String cpf, 
			@Param("valor") String valor);
	
	@Transactional(readOnly = true)
	Funcionario findByPis(String pis);
	
	@Transactional(readOnly = true)
	Funcionario findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Funcionario findByEmail(String email);
	
	@Query("SELECT f FROM Funcionario f WHERE f.empresa.id =:empresa_id")
	@Transactional(readOnly = true)
	Page<Funcionario> findByEmpresa(@Param("empresa_id") Long empresa_id, Pageable pageable);
}
