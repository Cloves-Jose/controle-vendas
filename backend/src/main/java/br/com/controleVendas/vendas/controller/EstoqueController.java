package br.com.controleVendas.vendas.controller;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;

import org.springframework.data.domain.Sort.Direction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleVendas.vendas.dto.EstoqueDto;
import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.EstoqueService;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class EstoqueController {
	
	private static Logger log = LoggerFactory.getLogger(EstoqueController.class);
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	/**
	 * Cadastrar um novo produto no bando de dados.
	 * 
	 * @param estoqueDto
	 * @param result
	 * @return EstoqueDto
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<EstoqueDto>> cadastrar(@Valid @RequestBody EstoqueDto estoqueDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando produto: {}", estoqueDto);
		Response<EstoqueDto> response = new Response<EstoqueDto>();
		
		validarDadosExistentes(estoqueDto, result);
		Estoque estoque = converterDtoParaEstoque(estoqueDto, result);
		if(result.hasErrors()) {
			log.error("Erro validando dados de produto {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
	
		estoqueService.persistir(estoque);
		
		response.setData(this.converterEstoqueDto(estoque));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Busca paginada de produtos da mesma marca
	 * 
	 * @param empresa_id
	 * @param marca
	 * @param pag
	 * @param ord
	 * @param dir
	 * @return EstoqueDto
	 */
	@GetMapping(value = "listaMarcas/{empresa_id}/{marca}")
	public ResponseEntity<Response<Page<EstoqueDto>>> buscarEstoque(
			@PathVariable("empresa_id") Long empresa_id,
			@PathVariable("marca") String marca,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir){
		log.info("Buscando por produtos associados ao cliente logado: {}, p??ginas: {}", empresa_id, marca);
		Response<Page<EstoqueDto>> response = new Response<Page<EstoqueDto>>();
		
		Page<Estoque> estoques = estoqueService.buscarPorMarca(marca, 
				PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord));
		Page<EstoqueDto> estoqueDto = estoques.map(estoque -> converterEstoqueDto(estoque));
		
		response.setData(estoqueDto);
		return ResponseEntity.ok(response);
	}

	
	/**
	 * Converter Estoque para EstoqueDto
	 * 
	 * @param estoque
	 * @return EstoqueDto
	 */
	private EstoqueDto converterEstoqueDto(Estoque estoque) {
		EstoqueDto estoqueDto = new EstoqueDto();
		
		estoqueDto.setId(estoque.getId());
		estoqueDto.setNome(estoque.getNome());
		estoqueDto.setFabricante(estoque.getFabricante());
		estoqueDto.setModelo(estoque.getModelo());
		estoqueDto.setNumeroDeSerie(estoque.getNumeroDeSerie());
		estoqueDto.setDimencoes(estoque.getDimencoes());
		estoqueDto.setLote(estoque.getLote());
		estoqueDto.setTipo(estoque.getTipo());
		estoqueDto.setValidade(estoque.getValidade());
		estoqueDto.setAtualizadoEm(estoque.getAtualizadoEm());
		estoqueDto.setCadastradoEm(estoque.getCadastradoEm());
		estoqueDto.setDeletadoEm(estoque.getDeletadoEm());
		return estoqueDto;
	}

	
	/**
	 * Converter EstoqueDto para Estoque
	 * 
	 * @param estoqueDto
	 * @param result
	 * @return Estoque
	 * @throws NoSuchAlgorithmException
	 */
	private Estoque converterDtoParaEstoque(EstoqueDto estoqueDto, 
			BindingResult result) throws NoSuchAlgorithmException {
			Estoque estoque = new Estoque();
			
			estoque.setNome(estoqueDto.getNome());
			estoque.setFabricante(estoqueDto.getFabricante());
			estoque.setModelo(estoqueDto.getModelo());
			estoque.setNumeroDeSerie(estoqueDto.getNumeroDeSerie());
			estoque.setDimencoes(estoqueDto.getDimencoes());
			estoque.setLote(estoqueDto.getLote());
			estoque.setTipo(TipoProdutoEnum.ELETRONICO);
			estoque.setValidade(estoqueDto.getValidade());
			estoque.setCadastradoEm(formatarData(new Date()));
		return estoque;
	}
	
	/**
	 * Formata a data antes de salvar no banco
	 * 
	 * @param data
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	private String formatarData(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(data);
	}
	
	/**
	 * Validar dados de empresa
	 * 
	 * @param estoqueDto
	 * @param result
	 */
	private void validarDadosExistentes(EstoqueDto estoqueDto, BindingResult result) {
		
		estoqueService.buscarPorNome(estoqueDto.getNome())
			.ifPresent(est -> result.addError(new ObjectError("estoque", "Produto j?? cadastrado no banco")));
		
	}
}
