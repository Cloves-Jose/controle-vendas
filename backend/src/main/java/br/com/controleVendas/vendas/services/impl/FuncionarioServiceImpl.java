package br.com.controleVendas.vendas.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.controleVendas.vendas.entities.Funcionario;
import br.com.controleVendas.vendas.repositoies.FuncionarioRepository;
import br.com.controleVendas.vendas.services.FuncionarioService;

public class FuncionarioServiceImpl implements FuncionarioService {
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	/**
	 * Salvando funcionário na base de dados
	 * 
	 * @param funcionario
	 * @return funcionario
	 */
	@Override
	public Funcionario persistir(Funcionario funcionario) {
		log.info("Salvando o funcionário {} na base de dados", funcionario.getNome());
		return this.funcionarioRepository.save(funcionario);
	}
	
	/**
	 * Indisponibiliza o funcionário atualizando o campo data
	 * 
	 * @param id
	 * @param data
	 * @return int
	 */
	@Override
	public int deletar(Long id, String data) {
		log.info("Deletando funcionário de id: {} da base de dados", id);
		return this.funcionarioRepository.deleteFuncionario(id, data);
	}

}
