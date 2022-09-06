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

import br.com.controleVendas.vendas.dto.ClienteDto;
import br.com.controleVendas.vendas.entities.CadastroPJ;
import br.com.controleVendas.vendas.enums.PerfilEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.ClienteService;
import br.com.controleVendas.vendas.utils.PasswordUtils;

/**
 * 
 * @author Cloves José
 * 
 *
 */

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	public ClienteController() {}
	
	/**
	 * Cadastrar um novo cliente no sistema.
	 * 
	 * @param clienteDto
	 * @param result
	 * @return ResposenEntity<Response<ClienteDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<ClienteDto>> cadastrar(@Valid @RequestBody ClienteDto cadastroClienteDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		log.info("Cadastrando Cliente: {}", cadastroClienteDto.toString());
		Response<ClienteDto> response = new Response<ClienteDto>();
		
		validarDadosExistentes(cadastroClienteDto, result);
		CadastroPJ cliente = this.converterDtoParaCliente(cadastroClienteDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.persistir(cliente);
		
		response.setData(this.converterClienteDto(cliente));
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
		Optional<CadastroPJ> cliente = this.clienteService.buscarPorId(id);
		
		if(!cliente.isPresent()) {
			log.info("Erro ao remover devido ao cliente ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover cliente. Resgistro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.deletar(id, formatarData(new Date()));
		return ResponseEntity.ok(new Response<String>());
	}
	
	/**
	 * Retorna a listagem de clientes cadastrados.
	 * 
	 * @param clienteId
	 * @return ResponseEntity<Response<ClienteDto>>
	 */
	@GetMapping(value = "/{clienteId}")
	public ResponseEntity<Response<ClienteDto>> buscarPorId(@PathVariable("clienteId") Long clienteId) {
		log.info("Buscando cliente por Id: {}", clienteId);
		Response<ClienteDto> response = new Response<ClienteDto>();
		Optional<CadastroPJ> cliente = this.clienteService.buscarPorId(clienteId);
		
		if(!cliente.isPresent()) {
			log.info("Cliente não encontrado para o ID: {}", clienteId);
			response.getErrors().add("Cliente não encontrado para o id " + clienteId);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterClienteDto(cliente.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna o cliente a partir do email 
	 * 
	 * @param email
	 * @return ResponseEntity<Response<ClienteDto>>
	 */
	@GetMapping(value = "email/{email}")
	public ResponseEntity<Response<ClienteDto>> buscaPorEmail(@PathVariable("email") String email) {
		log.info("Buscando cliente por email: {}", email);
		Response<ClienteDto> response = new Response<ClienteDto>();
		Optional<CadastroPJ> cliente = this.clienteService.buscarPorEmail(email);
		
		if(!cliente.isPresent()) {
			log.info("Cliente não encontrado para o email: {}", email);
			response.getErrors().add("Cliente não encontrado para o email " + email);
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterClienteDto(cliente.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do cliente
	 * @param cliente
	 * @return
	 */
	private ClienteDto converterClienteDto(CadastroPJ cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome_fantasia(cliente.getNome_fantasia());
		clienteDto.setRazao_social(cliente.getRazao_social());
		clienteDto.setCnpj(cliente.getCnpj());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setCriadoEm(cliente.getCriadoEm());
		clienteDto.setPerfil(cliente.getPerfil());
		clienteDto.setAtualizadoEm(cliente.getAtualizadoEm());
		clienteDto.setDeletadoEm(cliente.getDeletadoEm());
		return clienteDto;
	}

	/**
	 * Converte os dados do DTO para cliente
	 * 
	 * @param cadastroClienteDto
	 * @param result
	 * @return Cliente
	 * @throws NoSuchAlgorithmException
	 */
	private CadastroPJ converterDtoParaCliente(@Valid ClienteDto cadastroClienteDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		CadastroPJ cliente = new CadastroPJ();
		cliente.setNome_fantasia(cadastroClienteDto.getNome_fantasia());
		cliente.setRazao_social(cadastroClienteDto.getRazao_social());
		cliente.setCnpj(cadastroClienteDto.getCnpj());
		cliente.setEmail(cadastroClienteDto.getEmail());
		cliente.setPerfil(PerfilEnum.ROLE_PJ);
		cliente.setSenha(PasswordUtils.gerarBCrypt(cadastroClienteDto.getSenha()));
		cliente.setCriadoEm(formatarData(new Date()));
		return cliente;
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
	private void validarDadosExistentes(ClienteDto clienteDto, BindingResult result) {
		
		this.clienteService.listarPorEmail(clienteDto.getEmail())
			.ifPresent(cli -> result.addError(new ObjectError("cliente", "Email já existente.")));
	}
}
