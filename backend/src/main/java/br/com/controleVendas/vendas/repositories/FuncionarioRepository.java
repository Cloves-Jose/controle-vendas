package br.com.controleVendas.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleVendas.vendas.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
