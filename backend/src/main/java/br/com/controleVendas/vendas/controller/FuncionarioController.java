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
		Funcionario funcionario = this.converterDtoParaFuncionario(funcionarioDto, result);
		
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
		
	}
}
