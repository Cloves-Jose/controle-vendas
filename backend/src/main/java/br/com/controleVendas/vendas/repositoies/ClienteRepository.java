package br.com.controleVendas.vendas.repositoies;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controleVendas.vendas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
