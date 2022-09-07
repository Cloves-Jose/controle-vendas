package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Empresa;
import br.com.controleVendas.vendas.repositories.EmpresaRepository;
import br.com.controleVendas.vendas.services.EmpresaService;

/**
 * 
 * @author Cloves José
 *
 */
@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	
	@Override
	public Optional<Empresa> buscarPorEmail(String email) {
		Empresa consulta = this.empresaRepository.findByEmail(email);
		
		if(consulta.getDeletadoEm() == null) {
			log.info("Buscando cliente pelo email {}", email);
			return Optional.ofNullable(consulta);
		}
		log.info("Cliente não encontrado {}", email);
		return Optional.empty();
	}
	
	@Override
	public Empresa persistir(Empresa cliente) {
		log.info("Persistindo cliente na base de dados {}", cliente);
		return this.empresaRepository.save(cliente);
	}
	
	
	@Override
	public void deletar(Long id, String data) {
		log.info("Deletando cliente da base de dados");
		this.empresaRepository.deleteCliente(id, data);
	}
	

	@Override
	public Optional<Empresa> listarPorEmail(String email) {
		log.info("Procurando cliente por email {}", email);
		return Optional.ofNullable(this.empresaRepository.findByEmail(email));
	}


	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Procurando por CNPJ: {}", cnpj);
		return Optional.ofNullable(this.empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Optional<Empresa> buscarPorId(Long id) {
		log.info("Procurando por ID: {}", id);
		return Optional.ofNullable(this.empresaRepository.getReferenceById(id));
	}

	@Override
	public void recuperarDeletado(String cnpj) {
		Empresa consulta = this.empresaRepository.findByCnpj(cnpj);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Recuperando cnpj: {} deletado", cnpj);
			this.empresaRepository.recuperarCliente(cnpj, null);
		}
		log.info("Não existe registro para o cnpj informado");
	}

}
