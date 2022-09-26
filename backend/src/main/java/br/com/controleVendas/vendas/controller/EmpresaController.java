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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleVendas.vendas.dto.EmpresaDto;
import br.com.controleVendas.vendas.entities.Empresa;
import br.com.controleVendas.vendas.enums.PerfilEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.EmpresaService;
import br.com.controleVendas.vendas.utils.PasswordUtils;



@RestController
@RequestMapping("/api/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {}
	
	/**
	 * Cadastrar um novo cliente no sistema.
	 * 
	 * @param clienteDto
	 * @param result
	 * @return ResposenEntity<Response<ClienteDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<EmpresaDto>> cadastrar(@Valid @RequestBody EmpresaDto empresaDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		log.info("Cadastrando empresa: {}", empresaDto.toString());
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		
		validarDadosExistentes(empresaDto, result);
		Empresa empresa = converterDtoParaEmpresa(empresaDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da empresa: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.persistir(empresa);
		
		response.setData(converterEmpresaDto(empresa));
		return ResponseEntity.ok(response);
		
	}
	
	/**
	 * Ativar PJ desativado.
	 * 
	 * @param cnpj
	 * @return
	 */
	
	@PostMapping(value = "ativar/{cnpj}")
	public ResponseEntity<Response<String>> recuperar(@PathVariable("cnpj") String cnpj) {
		log.info("Recuperando PJ: {}", cnpj);
		Response<String> response = new Response<String>();
		Optional<Empresa> empresa = empresaService.listarPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			log.info("Erro ao recuperar empresa desativada. CNPJ: {} inválido", cnpj);
			response.getErrors().add("Erro ao recuperar empresa. Registro não encontrado para o CNPJ: " + cnpj);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(empresa.filter(cliPJ -> cliPJ.getDeletadoEm() == null).isPresent()) {
			log.info("A empresa já encontra-se ativa");
			response.getErrors().add("Erro ao recuperar empresa. A empresa encontra-se ativada.");
			return ResponseEntity.badRequest().body(response);
		}
		
		empresaService.recuperarDeletado(cnpj);
		log.info("Empresa ativada com sucesso!");
		response.setData("Empresa ativada com sucesso!");
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Desativa um cliente por ID
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Remover cliente: {}", id);
		Response<String> response = new Response<String>();
		Optional<Empresa> empresa = empresaService.listarPorId(id);
		
		if(!empresa.isPresent()) {
			log.info("Erro ao remover empresa devido ao ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover empresa. Resgistro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		if(empresa.filter(cliPJ -> cliPJ.getDeletadoEm() != null).isPresent()) {
			log.info("A empresa já encontra-se desativada.");
			response.getErrors().add("Erro ao remover empresa. A empresa já foi removido.");
			return ResponseEntity.badRequest().body(response);
		}
		
		this.empresaService.deletar(id, formatarData(new Date()));
		log.info("Empresa deletada com sucesso");
		response.setData("Empresa deletada com sucesso");
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna a listagem de clientes cadastrados.
	 * 
	 * @param clienteId
	 * @return ResponseEntity<Response<ClienteDto>>
	 */
	@GetMapping(value = "/{clienteId}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorId(@PathVariable("clienteId") Long clienteId) {
		log.info("Buscando cliente por Id: {}", clienteId);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.listarPorId(clienteId);

		if(!empresa.isPresent()) {
			log.info("Empresa não encontrado para o ID: {}", clienteId);
			response.getErrors().add("Empresa não encontrado para o id " + clienteId);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna o cliente a partir do email 
	 * 
	 * @param email
	 * @return ResponseEntity<Response<ClienteDto>>
	 */
	@GetMapping(value = "email/{email}")
	public ResponseEntity<Response<EmpresaDto>> buscaPorEmail(@PathVariable("email") String email) {
		log.info("Buscando cliente por email: {}", email);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaService.listarPorEmail(email);
		
		if(!empresa.isPresent()) {
			log.info("Empresa não encontrada para o email: {}", email);
			response.getErrors().add("Empresa não encontrado para o email " + email);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do cliente
	 * @param cliente
	 * @return
	 */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setNome_fantasia(empresa.getNome_fantasia());
		empresaDto.setRazao_social(empresa.getRazao_social());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setEmail(empresa.getEmail());
		empresaDto.setCriadoEm(empresa.getCriadoEm());
		empresaDto.setPerfil(empresa.getPerfil());
		empresaDto.setAtualizadoEm(empresa.getAtualizadoEm());
		empresaDto.setDeletadoEm(empresa.getDeletadoEm());
		return empresaDto;
	}

	/**
	 * Converte os dados do DTO para cliente
	 * 
	 * @param cadastroClienteDto
	 * @param result
	 * @return Cliente
	 * @throws NoSuchAlgorithmException
	 */
	private Empresa converterDtoParaEmpresa(@Valid EmpresaDto empresaDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		Empresa empresa = new Empresa();
		empresa.setNome_fantasia(empresaDto.getNome_fantasia());
		empresa.setRazao_social(empresaDto.getRazao_social());
		empresa.setCnpj(empresaDto.getCnpj());
		empresa.setEmail(empresaDto.getEmail());
		empresa.setPerfil(PerfilEnum.ROLE_PJ);
		empresa.setSenha(PasswordUtils.gerarBCrypt(empresaDto.getSenha()));
		empresa.setCriadoEm(formatarData(new Date()));
		return empresa;
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
	 * Verifica se o cliente está cadastrado e se não existe na base de dados.
	 * 
	 * @param clienteDto
	 * @param result
	 */
	private void validarDadosExistentes(EmpresaDto empresaDto, BindingResult result) {
		
		this.empresaService.listarPorEmail(empresaDto.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("empresa", "Email já existente.")));
		
		this.empresaService.listarPorCnpj(empresaDto.getCnpj())
			.ifPresent(cadPj -> result.addError(new ObjectError("empresa", "CNPJ já cadastrado")));
	}
}
