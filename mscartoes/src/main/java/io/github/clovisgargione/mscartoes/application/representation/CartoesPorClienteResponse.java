package io.github.clovisgargione.mscartoes.application.representation;

import java.math.BigDecimal;

import io.github.clovisgargione.mscartoes.domain.BandeiraCartao;
import io.github.clovisgargione.mscartoes.domain.ClienteCartao;

public class CartoesPorClienteResponse {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal limiteLiberado;
    
    public CartoesPorClienteResponse() {
	super();
    }

    public CartoesPorClienteResponse(String nome, BandeiraCartao bandeira, BigDecimal limiteLiberado) {
	super();
	this.nome = nome;
	this.bandeira = bandeira;
	this.limiteLiberado = limiteLiberado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BandeiraCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(BandeiraCartao bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limiteLiberado) {
        this.limiteLiberado = limiteLiberado;
    }
    
    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
	return new CartoesPorClienteResponse(model.getCartao().getNome(), model.getCartao().getBandeira(), model.getLimite());
    }
}
