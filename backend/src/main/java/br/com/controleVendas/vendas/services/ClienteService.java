package br.com.controleVendas.vendas.services;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Cliente;

public interface ClienteService {
	
	Optional<Cliente> buscarPorEmail(String email);
	
	Optional<Cliente> buscarPorId(String id);
	
	Optional<Cliente> buscarPorNome(String nome);

}
