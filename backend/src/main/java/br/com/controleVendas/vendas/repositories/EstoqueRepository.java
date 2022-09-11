package br.com.controleVendas.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controleVendas.vendas.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	
	
}
