package br.com.controleVendas.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleVendas.vendas.entities.EmpresaEstoque;

@Repository
public interface EmpresaEstoqueRepository extends JpaRepository<EmpresaEstoque, Long>{
	
}
