package io.github.clovisgargione.msavaliadorcredito.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.github.clovisgargione.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import io.github.clovisgargione.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import io.github.clovisgargione.msavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import io.github.clovisgargione.msavaliadorcredito.domain.model.Cartao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.CartaoAprovado;
import io.github.clovisgargione.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.clovisgargione.msavaliadorcredito.domain.model.DadosCliente;
import io.github.clovisgargione.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.github.clovisgargione.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.clovisgargione.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.clovisgargione.msavaliadorcredito.infra.clients.ClienteResourceClient;
import io.github.clovisgargione.msavaliadorcredito.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;

@Service
public class AvaliadorCreditoService {

    private ClienteResourceClient clienteResourceClient;
    private CartoesResourceClient cartoesResourceClient;
    private SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient,
	    CartoesResourceClient cartoesResourceClient, SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher) {
	super();
	this.clienteResourceClient = clienteResourceClient;
	this.cartoesResourceClient = cartoesResourceClient;
	this.solicitacaoEmissaoCartaoPublisher = solicitacaoEmissaoCartaoPublisher;
    }

    public SituacaoCliente obterSituacaoCliente(String cpf)
	    throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
	try {
	    ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
	    ResponseEntity<List<CartaoCliente>> cartaoClienteResponse = cartoesResourceClient.getCartoesByCliente(cpf);
	    return new SituacaoCliente.Builder().cliente(dadosClienteResponse.getBody())
		    .cartoes(cartaoClienteResponse.getBody()).build();
	} catch (FeignClientException fce) {
	    int status = fce.status();
	    if (HttpStatus.NOT_FOUND.value() == status) {
		throw new DadosClienteNotFoundException();
	    }
	    throw new ErroComunicacaoMicroservicesException(fce.getMessage(), status);
	}
    }
    
    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
	try {
	    ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
	    ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaAte(renda);
	    List<Cartao> cartoes = cartoesResponse.getBody();
	    List<CartaoAprovado> aprovados = cartoes.stream().map(cartao -> {
		DadosCliente dadosCliente = dadosClienteResponse.getBody();
		BigDecimal limiteBasico = cartao.getLimiteBasico();
		BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
		BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
		BigDecimal limiteAprovado = fator.multiply(limiteBasico);
		
		CartaoAprovado aprovado = new CartaoAprovado();
		aprovado.setCartao(cartao.getNome());
		aprovado.setBandeira(cartao.getBandeira());
		aprovado.setLimiteAprovado(limiteAprovado);
		return aprovado;
	    }).collect(Collectors.toList());
	    return new RetornoAvaliacaoCliente.Builder().cartoes(aprovados).build();
	} catch (FeignClientException fce) {
	    int status = fce.status();
	    if (HttpStatus.NOT_FOUND.value() == status) {
		throw new DadosClienteNotFoundException();
	    }
	    throw new ErroComunicacaoMicroservicesException(fce.getMessage(), status);
	}
    }
    
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) {
	try {
	    solicitacaoEmissaoCartaoPublisher.solicitarCartao(dadosSolicitacaoEmissaoCartao);
	    String protocolo = UUID.randomUUID().toString();
	    return new ProtocoloSolicitacaoCartao(protocolo);
	} catch(Exception e) {
	    throw new ErroSolicitacaoCartaoException(e.getMessage());
	}
    }
}
