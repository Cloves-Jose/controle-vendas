package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.controleVendas.vendas.entities.Funcionario;
import br.com.controleVendas.vendas.repositories.FuncionarioRepository;
import br.com.controleVendas.vendas.services.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private static Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Cadastrando funcionario na base de dados");
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public void deletar(String data, Long id) {
		log.info("Desativando funcionario da base de dados");
		funcionarioRepository.deletarFuncionario(data, id);
		
	}

	@Override
	public Optional<Funcionario> buscarPorPis(String pis) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Funcionario> buscarPorCargo(String cargo) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void ativar(String cpf) {
		log.info("Ativando o funcionario na base de dados");
		funcionarioRepository.ativarFuncionario(cpf, null);
		
	}

}
