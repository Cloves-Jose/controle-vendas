package br.com.controleVendas.vendas.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controleVendas.vendas.entities.Cliente;
import br.com.controleVendas.vendas.repositoies.ClienteRepository;
import br.com.controleVendas.vendas.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	private Date data = new Date();
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		Cliente consulta = this.clienteRepository.findByEmail(email);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Cliente {} não encontrado", email);
		}
		log.info("Buscando cliente pelo email {}", email);
		return Optional.ofNullable(consulta);
		//return Optional.ofNullable(this.clienteRepository.findByEmail(email));
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		Cliente consulta = this.clienteRepository.getReferenceById(id);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Cliente {} não encontrado", id);
		}
		log.info("Buscando cliente pelo id {}", id);
		return Optional.ofNullable(consulta);
		//return Optional.ofNullable(this.clienteRepository.getReferenceById(id));
	}

	@Override
	public Optional<Cliente> buscarPorNome(String nome) {
		Cliente consulta = this.clienteRepository.findByNomeStartsWith(nome);
		
		if(consulta.getDeletadoEm() != null) {
			log.info("Cliente {} não encontrado", nome);
		}
		log.info("Buscando cliente por nome {}", nome);
		return Optional.ofNullable(consulta);
		//return Optional.ofNullable(this.clienteRepository.findByNomeStartsWith(nome));
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo cliente na base de dados {}", cliente);
		return this.clienteRepository.save(cliente);
	}

	@Override
	public void deletar(Long id) {
		log.info("Deletando cliente da base de dados");
		String dataDeletada = this.formatDate(this.data);
		this.clienteRepository.deleteCliente(id, dataDeletada);
		
	}
	
	/**
	 * Formata a data para salvar no banco de dados
	 * 
	 * @param data
	 * @return "yyyy-MM-dd HH:mm:ss"
	 */
	private String formatDate(Date data) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(data);
	}
}
