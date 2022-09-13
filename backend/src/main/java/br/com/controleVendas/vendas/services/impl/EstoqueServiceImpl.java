package br.com.controleVendas.vendas.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Estoque buscarPorNome(String nome) {
		log.info("Buscando por registro na base de dados");
		return estoqueRepository.findByNome(nome);
	}

}
