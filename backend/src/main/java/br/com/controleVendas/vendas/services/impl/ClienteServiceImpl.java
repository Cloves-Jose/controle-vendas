package br.com.controleVendas.vendas.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Cliente;
import br.com.controleVendas.vendas.repositoies.ClienteRepository;
import br.com.controleVendas.vendas.services.ClienteService;

/**
 * 
 * @author Cloves José
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService{
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Retorna o cliente por email
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		Cliente consulta = this.clienteRepository.findByEmail(email);
		
		if(consulta.getDeletadoEm() == null) {
			log.info("Buscando cliente pelo email {}", email);
			return Optional.ofNullable(consulta);
		}
		log.info("Cliente não encontrado {}", email);
		return Optional.empty();
	}
	
	/**
	 * Retorna o cliente por id
	 * 
	 * @param clienteId
	 * @return Optional<Cliente>
	 */
	@Override
	public Optional<Cliente> buscarPorId(Long clienteId) {
		Cliente consulta = this.clienteRepository.findByClienteId(clienteId);
		
		if(consulta.getDeletadoEm() == null) {
			log.info("Buscando cliente pelo id {}", clienteId);
			return Optional.ofNullable(consulta);
			
		}
		log.info("Cliente de id: {} não encontrado", clienteId);
		return Optional.empty();
	}
	
	/**
	 * Persiste o cliente no banco de dados
	 * 
	 * @param cliente
	 * @return Cliente
	 */
	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo cliente na base de dados {}", cliente);
		return this.clienteRepository.save(cliente);
	}
	
	/**
	 * Desativa o cliente no banco de dados
	 * 
	 * @param id
	 * @param data
	 * @return int
	 */
	@Override
	public int deletar(Long id, String data) {
		log.info("Deletando cliente da base de dados");
		return this.clienteRepository.deleteCliente(id, data);
	}
}
