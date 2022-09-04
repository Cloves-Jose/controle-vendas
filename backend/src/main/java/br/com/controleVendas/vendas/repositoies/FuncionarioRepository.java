package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	
}
