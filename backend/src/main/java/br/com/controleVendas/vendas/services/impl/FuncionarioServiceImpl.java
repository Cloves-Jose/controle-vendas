package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Funcionario;
import br.com.controleVendas.vendas.repositories.FuncionarioRepository;
import br.com.controleVendas.vendas.services.FuncionarioService;

@Service
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
	public Optional<Funcionario> listarPorPis(String pis) {
		Funcionario consulta = funcionarioRepository.findByPis(pis);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Buscando por funcionario de PIS: {}", pis);
			return Optional.ofNullable(consulta);
		}
		
		log.info("Nenhum funcionario encontrado.");
		return Optional.empty();
	}

	@Override
	public Optional<Funcionario> listarPorCpf(String cpf) {
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

	@Override
	public Optional<Funcionario> buscarPorPis(String pis) {
		log.info("Buscando por funcionario de PIS: {}", pis);
		return Optional.ofNullable(funcionarioRepository.findByPis(pis));
	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando por funcionario de CPF: {}", cpf);
		return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando por funcion??rio de Email: {}", email);
		return Optional.ofNullable(funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> listarPorId(Long id) {
		Funcionario consulta = funcionarioRepository.getReferenceById(id);
		
		if(consulta.getDeletadoEm() == null) {
			log.info("Buscando por funcionario de id: {}", id);
			return Optional.ofNullable(consulta);
		}
		return Optional.empty();
	}

	@Override
	public Page<Funcionario> listarAssociados(Long empresa_id, PageRequest pageRequest) {
		Page<Funcionario> consulta = funcionarioRepository.findByEmpresa(empresa_id, pageRequest);
		
		if(consulta.isEmpty()) {
			log.info("Nenhum registro encontrado");
			return Page.empty();
		}
	
		log.info("Buscando por funcion??rio na base de dados");
		return consulta;
	}
}
