package io.github.clovisgargione.mscartoes.infra.mqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.clovisgargione.mscartoes.domain.Cartao;
import io.github.clovisgargione.mscartoes.domain.ClienteCartao;
import io.github.clovisgargione.mscartoes.domain.DadosSolicitacaoEmissaoCartao;
import io.github.clovisgargione.mscartoes.infra.repository.CartaoRepository;
import io.github.clovisgargione.mscartoes.infra.repository.ClienteCartaoRepository;

@Component
public class EmissaoCartaoSubscriber {
    
    private static final Logger logger = LoggerFactory.getLogger(EmissaoCartaoSubscriber.class);
    
    private CartaoRepository cartaoRepository;
    private ClienteCartaoRepository clienteCartaorepository;
    
    public EmissaoCartaoSubscriber(CartaoRepository cartaoRepository, ClienteCartaoRepository clienteCartaorepository) {
	super();
	this.cartaoRepository = cartaoRepository;
	this.clienteCartaorepository = clienteCartaorepository;
    }

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload String payload) {
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
	    Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
	    ClienteCartao clienteCartao = new ClienteCartao();
	    clienteCartao.setCartao(cartao);
	    clienteCartao.setCpf(dados.getCpf());
	    clienteCartao.setLimite(dados.getLimiteLiberado());
	    clienteCartaorepository.save(clienteCartao);
	} catch(Exception e) {
	    logger.error("Erro ao receber solicitação de emissão de cartão: {}", e.getMessage());
	}
    }
}
