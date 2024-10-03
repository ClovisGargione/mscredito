package io.github.clovisgargione.mscartoes.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.clovisgargione.mscartoes.application.representation.CartaoSaveRequest;
import io.github.clovisgargione.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.clovisgargione.mscartoes.domain.Cartao;
import io.github.clovisgargione.mscartoes.domain.ClienteCartao;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cartoes")
@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    private CartaoService cartaoService;
    private ClienteCartaoService clienteCartaoService;

    public CartoesResource(CartaoService service, ClienteCartaoService clienteCartaoService) {
	super();
	this.cartaoService = service;
	this.clienteCartaoService = clienteCartaoService;
    }

    @PostMapping
    public ResponseEntity<?> cadastro(@RequestBody CartaoSaveRequest cartaoSaveRequest) {
	Cartao cartao = cartaoSaveRequest.toModel();
	cartaoService.save(cartao);
	return ResponseEntity.status(HttpStatus.CREATED).body(cartao);
    }

    @GetMapping(params = "renda")
    public ResponseEntity<?> getCartoesRendaAte(@RequestParam("renda") Long renda) {
	List<Cartao> cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
	return ResponseEntity.ok(cartoes);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> getCartoesByCliente(@RequestParam("cpf") String cpf) {
	List<ClienteCartao> lista = clienteCartaoService.listarCartoesByCpf(cpf);
	List<CartoesPorClienteResponse> resultList = lista.stream().map(CartoesPorClienteResponse::fromModel)
		.collect(Collectors.toList());
	return ResponseEntity.ok(resultList);
    }
}
