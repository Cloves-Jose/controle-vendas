package br.com.controleVendas.vendas.services.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.repositories.EstoqueRepository;
import br.com.controleVendas.vendas.services.EstoqueService;

@Service
public class EstoqueServiceImpl implements EstoqueService{
	
	private static Logger log = LoggerFactory.getLogger(EstoqueServiceImpl.class);
	
	@Autowired
	private EstoqueRepository estoqueRepository;

	@Override
	public Estoque persistir(Estoque estoque) {
		log.info("Persistindo produto na base de dados");
		return estoqueRepository.save(estoque);
	}

	@Override
	public Optional<Estoque> buscarPorNome(String nome, Long empresa_id) {
		log.info("Buscando por registro na base de dados");
		return estoqueRepository.findByNome(nome, empresa_id);
	}

	@Override
	public Page<Estoque> buscarPorMarca(String marca, Long empresa_id, PageRequest pageResquest) {
		Page<Estoque> consulta = estoqueRepository.findByMarca(marca, empresa_id, pageResquest);
		
		if(consulta.isEmpty()) {
			log.info("Nenhum registro encontrado");
			return Page.empty();
		}
		
		log.info("Buscando por funcionário na base de dados");
		return consulta;
	}

	@Override
	public boolean validarData(String data) {
		if(data.equalsIgnoreCase("YYYY-MM-DD")) {
			log.info("Data está no formato incorreto");
			return true;
		}
		return false;
	}

}
