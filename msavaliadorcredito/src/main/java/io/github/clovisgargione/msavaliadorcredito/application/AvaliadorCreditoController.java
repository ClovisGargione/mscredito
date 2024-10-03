package io.github.clovisgargione.msavaliadorcredito.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.clovisgargione.msavaliadorcredito.application.exception.DadosClienteNotFoundException;
import io.github.clovisgargione.msavaliadorcredito.application.exception.ErroComunicacaoMicroservicesException;
import io.github.clovisgargione.msavaliadorcredito.application.exception.ErroSolicitacaoCartaoException;
import io.github.clovisgargione.msavaliadorcredito.domain.model.DadosAvaliacao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao;
import io.github.clovisgargione.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.github.clovisgargione.msavaliadorcredito.domain.model.SituacaoCliente;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Avaliações de crédito")
@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private AvaliadorCreditoService avaliadorCreditoService;
    
    public AvaliadorCreditoController(AvaliadorCreditoService avaliadorCreditoService) {
	super();
	this.avaliadorCreditoService = avaliadorCreditoService;
    }



    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
	try {
	    SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
	    return ResponseEntity.ok(situacaoCliente);
	} catch (DadosClienteNotFoundException dnf) {
	    return ResponseEntity.notFound().build();
	} catch(ErroComunicacaoMicroservicesException e) {
	    return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
	}
	
    }
    
    @PostMapping
    public ResponseEntity<?> realizarAvaliacao(@RequestBody DadosAvaliacao dados){
	try {
	    RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
	    return ResponseEntity.ok(retornoAvaliacaoCliente);
	} catch (DadosClienteNotFoundException dnf) {
	    return ResponseEntity.notFound().build();
	} catch(ErroComunicacaoMicroservicesException e) {
	    return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
	}
    }
    
    @PostMapping("solicitacoes-cartao")
    public ResponseEntity<?> solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) {
	try {
	    ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarEmissaoCartao(dadosSolicitacaoEmissaoCartao);
	    return ResponseEntity.ok(protocolo);
	} catch (ErroSolicitacaoCartaoException e) {
	    return ResponseEntity.internalServerError().body(e.getMessage()); 
	}
    }
}
