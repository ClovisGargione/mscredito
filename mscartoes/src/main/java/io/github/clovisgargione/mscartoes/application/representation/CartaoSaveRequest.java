package io.github.clovisgargione.mscartoes.application.representation;

import java.math.BigDecimal;

import io.github.clovisgargione.mscartoes.domain.BandeiraCartao;
import io.github.clovisgargione.mscartoes.domain.Cartao;

public class CartaoSaveRequest {

    private String nome;
    
    private BandeiraCartao bandeira;
    
    private BigDecimal renda;
    
    private BigDecimal limite;

    public CartaoSaveRequest() {
	super();
    }

    public CartaoSaveRequest(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limite) {
	super();
	this.nome = nome;
	this.bandeira = bandeira;
	this.renda = renda;
	this.limite = limite;
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

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }
    
    public Cartao toModel() {
	return new Cartao(nome, bandeira, renda, limite);
    }
}
