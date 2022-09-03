package br.com.controleVendas.vendas.controller;

import java.security.NoSuchAlgorithmException;
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

import br.com.controleVendas.vendas.dto.ClienteDto;
import br.com.controleVendas.vendas.entities.Cliente;
import br.com.controleVendas.vendas.enums.PerfilEnum;
import br.com.controleVendas.vendas.response.Response;
import br.com.controleVendas.vendas.services.ClienteService;
import br.com.controleVendas.vendas.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-cl")
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
		Cliente cliente = this.converterDtoParaCliente(cadastroClienteDto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Cliente: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.clienteService.persistir(cliente);
		
		response.setData(this.converterClienteDto(cliente));
		return ResponseEntity.ok(response);
		
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do cliente
	 * @param cliente
	 * @return
	 */
	private ClienteDto converterClienteDto(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setSobrenome(cliente.getSobrenome());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setCriadoEm(cliente.getCriadoEm());
		clienteDto.setAtualizadoEm(cliente.getAtualizadoEm());
		clienteDto.setDeletadoEm(clienteDto.getDeletadoEm());
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
	private Cliente converterDtoParaCliente(@Valid ClienteDto cadastroClienteDto, BindingResult result) 
		throws NoSuchAlgorithmException {
		Cliente cliente = new Cliente();
		cliente.setNome(cadastroClienteDto.getNome());
		cliente.setSobrenome(cadastroClienteDto.getSobrenome());
		cliente.setEmail(cadastroClienteDto.getEmail());
		cliente.setPerfil(PerfilEnum.ROLE_ADMIN);
		cliente.setSenha(PasswordUtils.gerarBCrypt(cadastroClienteDto.getSenha()));
		cliente.setCriadoEm(new Date());
		return cliente;
	}

	/**
	 * Verifica se o cliente está cadastrado e se não existe na base de dados.
	 * 
	 * @param clienteDto
	 * @param result
	 */
	private void validarDadosExistentes(ClienteDto clienteDto, BindingResult result) {
		Optional<Cliente> cliente = this.clienteService.buscarPorEmail(clienteDto.getEmail());
		if(!cliente.isPresent()) {
			result.addError(new ObjectError("cliente", "Cliente já cadastrado"));
		}
		
		cliente.ifPresent(cli -> result.addError(new ObjectError("cliente", "Email já existente.")));
	}
}
