package br.com.controleVendas.vendas.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.EmpresaEstoque;
import br.com.controleVendas.vendas.repositories.EmpresaEstoqueRepository;
import br.com.controleVendas.vendas.services.EmpresaEstoqueService;

@Service
public class EmpresaEstoqueServiceImpl implements EmpresaEstoqueService{
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaEstoqueService.class);
	
	@Autowired
	private EmpresaEstoqueRepository empresaEstoqueRepository;

	@Override
	public EmpresaEstoque persistir(EmpresaEstoque empresaEstoque) {
		log.info("Vinculando produto a empresa");
		return empresaEstoqueRepository.save(empresaEstoque);
	}


}
