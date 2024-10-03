package io.github.clovisgargione.msavaliadorcredito.domain.model;

public class ProtocoloSolicitacaoCartao {

    private String protocolo;

    public ProtocoloSolicitacaoCartao(String protocolo) {
	super();
	this.protocolo = protocolo;
    }

    public ProtocoloSolicitacaoCartao() {
	super();
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
