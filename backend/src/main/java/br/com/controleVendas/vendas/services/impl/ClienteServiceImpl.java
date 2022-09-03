package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import br.com.controleVendas.vendas.entities.Cliente;
import br.com.controleVendas.vendas.services.ClienteService;

public class ClienteServiceImpl implements ClienteService{

	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Cliente> buscarPorId(String id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Cliente> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente atualizar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
