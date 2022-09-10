package br.com.controleVendas.vendas.controller;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleVendas.vendas.dto.FuncionarioDto;
import br.com.controleVendas.vendas.entities.Empresa;
import br.com.controleVendas.vendas.entities.Funcionario;
import br.com.controleVendas.vendas.enums.PerfilEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.EmpresaService;
import br.com.controleVendas.vendas.services.FuncionarioService;
import br.com.controleVendas.vendas.utils.PasswordUtils;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {
	
	private static Logger log = LoggerFactory.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	/**
	 * Cadastra um novo funcionario no banco de dados.
	 * 
	 * @param funcionarioDto
	 * @param result
	 * @return FuncionarioDto
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<FuncionarioDto>> cadastrar(@Valid @RequestBody FuncionarioDto funcionarioDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando funcionario: {}", funcionarioDto.toString());
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();
		
		validarDadosExistentes(funcionarioDto, result);
		Funcionario funcionario = converterDtoParaFuncionario(funcionarioDto, result);
		
		if(result.hasErrors()) {
			log.error("Erro validando dados de funcionario {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Empresa> empresa = empresaService.buscarPorCnpj(funcionarioDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		funcionarioService.persistir(funcionario);
		
		response.setData(this.converterFuncionarioDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Desativar um funcionário por ID
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Remover funcionario: {}", id);
		Response<String> response = new Response<String>();
		Optional<Funcionario> funcionario = funcionarioService.listarPorId(id);
		
		if(!funcionario.isPresent()) {
			log.info("Erro ao remover funcionário devido ao ID: {} ser inválido", id);
			response.getErrors().add("Erro ao remover funcionário. Funcionário não econtrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(funcionario.filter(func -> func.getDeletadoEm() != null).isPresent()) {
			log.info("Funcionário já encontra-se desativado");
			response.getErrors().add("Erro ao remover funcionário. O Funcionário já foi removido");
			return ResponseEntity.badRequest().body(response);
		}
		
		funcionarioService.deletar(formatarData(new Date()), id);
		log.info("Funcionario deletado com sucesso");
		response.setData("Funcionário deletado com sucesso");
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Recuperar funcionário na base de dados.
	 * 
	 * @param cpf
	 * @return
	 */
	@PostMapping(value = "ativar/{cpf}")
	public ResponseEntity<Response<String>> recuperar(@PathVariable("cpf") String cpf) {
		log.info("Recuperando funcionário de cpf: {}", cpf);
		Response<String> response = new Response<String>();
		Optional<Funcionario> funcionario = funcionarioService.buscarPorCpf(cpf);
		
		if(!funcionario.isPresent()) {
			log.info("Erro ao recuperar funcionário desativado. CPF {} inválido", cpf);
			response.getErrors().add("Erro ao recuperar funcionário. Registro não encontrado para o CPF: " + cpf);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(funcionario.filter(func -> func.getDeletadoEm() == null).isPresent()) {
			log.info("O funcionário já encontra-se ativo");
			response.getErrors().add("Erro ao recuperar funcionário. O funcionário encontra-se ativado.");
			return ResponseEntity.badRequest().body(response);
		}
		
		funcionarioService.ativar(cpf);
		log.info("Funcionário ativado com sucesso!");
		response.setData("Funcionário ativado com sucesso!");
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem paginada de funcionário vinculados a empresa.
	 * 
	 * @param empresa_id
	 * @return FuncionarioDto
	 */
	@GetMapping(value = "listaFuncionarios/{empresa_id}")
	public ResponseEntity<Response<Page<FuncionarioDto>>> buscarAssociados(
			@PathVariable("empresa_id") Long empresa_id,
			@RequestParam(value = "pag", defaultValue = "0") int pag,
			@RequestParam(value = "ord", defaultValue = "id") String ord,
			@RequestParam(value = "dir", defaultValue = "DESC") String dir) {
		log.info("Buscando por funcionários associados ao cliente logado: {}, página: {}", empresa_id, pag);
		Response<Page<FuncionarioDto>> response = new Response<Page<FuncionarioDto>>();
		
		Page<Funcionario> funcionarios = funcionarioService.listarAssociados(empresa_id,
				PageRequest.of(pag, this.qtdPorPagina, Direction.valueOf(dir), ord));
		Page<FuncionarioDto> funcionariosDto = funcionarios.map(funcionario -> converterFuncionarioDto(funcionario));
		
		response.setData(funcionariosDto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Converte os dados de Funcionario para Dto
	 * 
	 * @param funcionario
	 * @return FuncionarioDto
	 */
	private FuncionarioDto converterFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setNome(funcionario.getNome());
		funcionarioDto.setSobrenome(funcionario.getSobrenome());
		funcionarioDto.setData_nascimento(funcionario.getData_nascimento());
		funcionarioDto.setNumero_residencia(funcionario.getNumero_residencia());
		funcionarioDto.setCpf(funcionario.getCpf());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setRua(funcionario.getRua());
		funcionarioDto.setBairro(funcionario.getBairro());
		funcionarioDto.setCidade(funcionario.getCidade());
		funcionarioDto.setEstado(funcionario.getEstado());
		funcionarioDto.setCep(funcionario.getCep());
		funcionarioDto.setAtualizadoEm(funcionario.getAtualizadoEm());
		funcionarioDto.setCriadoEm(funcionario.getCriadoEm());
		funcionarioDto.setDeletadoEm(funcionario.getDeletadoEm());
		funcionarioDto.setPis(funcionario.getPis());
		funcionarioDto.setPerfil(funcionario.getPerfil());
		return funcionarioDto;
	}

	/**
	 * Converte os dados do DTO para funcionário
	 * 
	 * @param funcionarioDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(@Valid FuncionarioDto funcionarioDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(funcionarioDto.getNome());
		funcionario.setSobrenome(funcionarioDto.getSobrenome());
		funcionario.setData_nascimento(funcionarioDto.getData_nascimento());
		funcionario.setNumero_residencia(funcionarioDto.getNumero_residencia());
		funcionario.setCpf(funcionarioDto.getCpf());
		funcionario.setEmail(funcionarioDto.getEmail());
		funcionario.setSenha(PasswordUtils.gerarBCrypt(funcionarioDto.getSenha()));
		funcionario.setRua(funcionarioDto.getRua());
		funcionario.setBairro(funcionarioDto.getBairro());
		funcionario.setCidade(funcionarioDto.getCidade());
		funcionario.setEstado(funcionarioDto.getEstado());
		funcionario.setCep(funcionarioDto.getCep());
		funcionario.setAtualizadoEm(funcionarioDto.getAtualizadoEm());
		funcionario.setCriadoEm(this.formatarData(new Date()));
		funcionario.setDeletadoEm(funcionario.getDeletadoEm());
		funcionario.setPis(funcionarioDto.getPis());
		funcionario.setCargo(funcionarioDto.getCargo());
		funcionario.setPerfil(PerfilEnum.ROLE_PF);
		return funcionario;
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
		
		funcionarioService.buscarPorEmail(funcionarioDto.getEmail())
			.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já cadastrado")));
		
	}
}
