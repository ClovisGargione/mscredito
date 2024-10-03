package io.github.clovisgargione.msclientes.application;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.clovisgargione.msclientes.application.representation.ClienteSaveRequest;
import io.github.clovisgargione.msclientes.domain.Cliente;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "Clientes")
@RestController
@RequestMapping("clientes")
public class ClientesResource {
    
    private ClienteService service;

    public ClientesResource(ClienteService service) {
	super();
	this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteSaveRequest clienteSaveRequest) {
	Cliente cliente = clienteSaveRequest.toModel();
	service.save(cliente);
	URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest().query("cpf={cpf}")
		.buildAndExpand(cliente.getCpf()).toUri();
	return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<?> dadosCliente(@RequestParam("cpf") String cpf) {
	Optional<Cliente> cliente = service.getByCpf(cpf);
	if (cliente.isEmpty()) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(cliente);
    }

    @GetMapping("server")
    @ResponseBody
    public String server(HttpServletRequest request) {
	return request.getServerName() + ":" + request.getServerPort();
    }
}
