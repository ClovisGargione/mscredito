package io.github.clovisgargione.mscartoes.application;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.clovisgargione.mscartoes.domain.ClienteCartao;
import io.github.clovisgargione.mscartoes.infra.repository.ClienteCartaoRepository;

@Service
public class ClienteCartaoService {

    private ClienteCartaoRepository repository;

    public ClienteCartaoService(ClienteCartaoRepository repository) {
	super();
	this.repository = repository;
    }
    
    public List<ClienteCartao> listarCartoesByCpf(String cpf){
	return repository.findByCpf(cpf);
    }
}
