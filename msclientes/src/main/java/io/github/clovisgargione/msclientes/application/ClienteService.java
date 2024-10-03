package io.github.clovisgargione.msclientes.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.clovisgargione.msclientes.domain.Cliente;
import io.github.clovisgargione.msclientes.infra.repository.ClienteRepository;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
	this.clienteRepository = clienteRepository;
    }
    
    public Cliente save(Cliente cliente) {
	return clienteRepository.save(cliente);
    }
    
    public Optional<Cliente> getByCpf(String cpf){
	return clienteRepository.findByCpf(cpf);
    }
}
