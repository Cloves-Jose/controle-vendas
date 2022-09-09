package br.com.controleVendas.vendas.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleVendas.vendas.dto.FuncionarioDto;
import br.com.controleVendas.vendas.entities.Empresa;
import br.com.controleVendas.vendas.entities.Funcionario;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.EmpresaService;
import br.com.controleVendas.vendas.services.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
	
	private static Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	/**
	 * Cadastra um novo funcionario no banco de dados.
	 * 
	 * @param funcionarioDto
	 * @param result
	 * @return FuncionarioDto
	 * @throws NoSuchAlgorithmException
	 */
	public ResponseEntity<Response<FuncionarioDto>> cadastrar(@Valid @RequestBody FuncionarioDto funcionarioDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando funcionario: {}", funcionarioDto.toString());
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		validarDadosExistentes(funcionarioDto, result);
		Funcionario funcionario = this.converterDtoParaFuncionario(funcionarioDto, result);
	}
	
	/**
	 * Checar se o funcionário já existe na base de dados.
	 * 
	 * @param funcionarioDto
	 * @param result
	 */
	private void validarDadosExistentes(FuncionarioDto funcionarioDto, BindingResult result) {
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(funcionarioDto.getCnpj()); 
		
		if(!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
		}
		
		funcionarioService.buscarPorCpf(funcionarioDto.getCpf())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já cadastrado")));
		
		funcionarioService.buscarPorPis(funcionarioDto.getPis())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "PIS já cadastrado")));
		
	}
}
