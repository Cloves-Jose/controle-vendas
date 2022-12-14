package br.com.controleVendas.vendas.services.impl;

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
	public Optional<Estoque> buscarPorNome(String nome) {
		log.info("Buscando por registro na base de dados");
		return estoqueRepository.findByNome(nome);
	}

	@Override
	public Page<Estoque> buscarPorMarca(String fabricante, PageRequest pageResquest) {
		Page<Estoque> consulta = estoqueRepository.findByMarca(fabricante, pageResquest);
		
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
