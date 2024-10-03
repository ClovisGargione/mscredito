package io.github.clovisgargione.msavaliadorcredito.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.clovisgargione.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

    private RabbitTemplate rabbitTemplate;
    private Queue queueEmissaoCartoes;

    public SolicitacaoEmissaoCartaoPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissaoCartoes) {
	super();
	this.rabbitTemplate = rabbitTemplate;
	this.queueEmissaoCartoes = queueEmissaoCartoes;
    }
    
    public void solicitarCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws JsonProcessingException {
	String json = convertIntoJson(dadosSolicitacaoEmissaoCartao);
	rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
    }
    
    private String convertIntoJson(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws JsonProcessingException{
	ObjectMapper mapper = new ObjectMapper();
	String json = mapper.writeValueAsString(dadosSolicitacaoEmissaoCartao);
	return json;
    }
    
}
