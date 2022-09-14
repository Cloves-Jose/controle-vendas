package br.com.controleVendas.vendas.controller;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleVendas.vendas.dto.EstoqueDto;
import br.com.controleVendas.vendas.entities.Empresa;
import br.com.controleVendas.vendas.entities.Estoque;
import br.com.controleVendas.vendas.enums.TipoProdutoEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.EmpresaService;
import br.com.controleVendas.vendas.services.EstoqueService;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class EstoqueController {
	
	private static Logger log = LoggerFactory.getLogger(EstoqueController.class);
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private EmpresaService empresaService;
	
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
		
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(estoqueDto.getCnpj());
		empresa.ifPresent(emp -> estoque.setEmpresa(emp));
		estoqueService.persistir(estoque);
		
		response.setData(this.converterEstoqueDto(estoque));
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
		estoqueDto.setMarca(estoque.getMarca());
		estoqueDto.setPreco(estoque.getPreco());
		estoqueDto.setQuantidade(estoque.getQuantidade());
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
			estoque.setMarca(estoqueDto.getMarca());
			estoque.setPreco(estoqueDto.getPreco());
			estoque.setQuantidade(estoqueDto.getQuantidade());
			estoque.setTipo(TipoProdutoEnum.ALIMENTOS);
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
		
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(estoqueDto.getCnpj());
		
		if(!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada"));
		}
		
		estoqueService.buscarPorNome(estoqueDto.getNome(), empresa.get().getId())
			.ifPresent(est -> result.addError(new ObjectError("estoque", "Produto já cadastrado na empresa")));
		
	}
}
