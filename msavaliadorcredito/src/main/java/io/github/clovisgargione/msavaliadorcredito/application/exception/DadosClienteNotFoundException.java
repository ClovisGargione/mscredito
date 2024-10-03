package io.github.clovisgargione.msavaliadorcredito.application.exception;

public class DadosClienteNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DadosClienteNotFoundException() {
	super("Dados do cliente não econtrados para o cpf informado.");
    }

    
}
