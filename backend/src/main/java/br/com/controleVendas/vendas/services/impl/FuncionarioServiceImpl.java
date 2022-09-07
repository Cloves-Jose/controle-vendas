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
		Funcionario consulta = funcionarioRepository.findByPis(pis);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Buscando por funcionario de PIS: {}", pis);
			return Optional.ofNullable(consulta);
		}
		
		log.info("Nenhum funcionario encontrado.");
		return Optional.empty();
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		Funcionario consulta = funcionarioRepository.findByCpf(cpf);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Buscando por funcionario de CPF: {}", cpf);
			return Optional.ofNullable(consulta);
		}
		return Optional.empty();
	}

	@Override
	public void ativar(String cpf) {
		log.info("Ativando o funcionario na base de dados");
		funcionarioRepository.ativarFuncionario(cpf, null);
		
	}

}
