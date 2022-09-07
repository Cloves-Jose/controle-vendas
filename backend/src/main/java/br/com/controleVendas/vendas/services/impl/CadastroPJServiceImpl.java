package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.CadastroPJ;
import br.com.controleVendas.vendas.repositories.CadastroPJRepository;
import br.com.controleVendas.vendas.services.CadastroPJService;

/**
 * 
 * @author Cloves José
 *
 */
@Service
public class CadastroPJServiceImpl implements CadastroPJService{
	
	private static final Logger log = LoggerFactory.getLogger(CadastroPJServiceImpl.class);
	
	@Autowired
	private CadastroPJRepository cadastroPJRepository;
	
	
	@Override
	public Optional<CadastroPJ> buscarPorEmail(String email) {
		CadastroPJ consulta = this.cadastroPJRepository.findByEmail(email);
		
		if(consulta.getDeletadoEm() == null) {
			log.info("Buscando cliente pelo email {}", email);
			return Optional.ofNullable(consulta);
		}
		log.info("Cliente não encontrado {}", email);
		return Optional.empty();
	}
	
	@Override
	public CadastroPJ persistir(CadastroPJ cliente) {
		log.info("Persistindo cliente na base de dados {}", cliente);
		return this.cadastroPJRepository.save(cliente);
	}
	
	
	@Override
	public void deletar(Long id, String data) {
		log.info("Deletando cliente da base de dados");
		this.cadastroPJRepository.deleteCliente(id, data);
	}
	

	@Override
	public Optional<CadastroPJ> listarPorEmail(String email) {
		log.info("Procurando cliente por email {}", email);
		return Optional.ofNullable(this.cadastroPJRepository.findByEmail(email));
	}


	@Override
	public Optional<CadastroPJ> buscarPorCnpj(String cnpj) {
		log.info("Procurando por CNPJ: {}", cnpj);
		return Optional.ofNullable(this.cadastroPJRepository.findByCnpj(cnpj));
	}

	@Override
	public Optional<CadastroPJ> buscarPorId(Long id) {
		log.info("Procurando por ID: {}", id);
		return Optional.ofNullable(this.cadastroPJRepository.getReferenceById(id));
	}

	@Override
	public void recuperarDeletado(String cnpj) {
		CadastroPJ consulta = this.cadastroPJRepository.findByCnpj(cnpj);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Recuperando cnpj: {} deletado", cnpj);
			this.cadastroPJRepository.recuperarCliente(cnpj, null);
		}
		log.info("Não existe registro para o cnpj informado");
	}

}
