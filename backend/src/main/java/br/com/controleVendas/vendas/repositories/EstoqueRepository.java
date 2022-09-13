package br.com.controleVendas.vendas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.controleVendas.vendas.entities.Estoque;

public interface EstoqueRepository extends MongoRepository<Estoque, Long>{
	
	Estoque findByNome(String nome);
}
