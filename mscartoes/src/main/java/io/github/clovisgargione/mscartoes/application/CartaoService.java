package io.github.clovisgargione.mscartoes.application;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.clovisgargione.mscartoes.domain.Cartao;
import io.github.clovisgargione.mscartoes.infra.repository.CartaoRepository;

@Service
public class CartaoService {

    private CartaoRepository repository;

    public CartaoService(CartaoRepository repository) {
	super();
	this.repository = repository;
    }
    
    public Cartao save(Cartao cartao) {
	return repository.save(cartao);
    }
    
    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
	BigDecimal rendaBigDecimal = BigDecimal.valueOf(renda);
	return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}
