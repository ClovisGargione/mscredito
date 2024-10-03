package io.github.clovisgargione.msavaliadorcredito.domain.model;

import java.util.List;

public class SituacaoCliente {

    private DadosCliente cliente;

    private List<CartaoCliente> cartoes;

    public SituacaoCliente(DadosCliente cliente, List<CartaoCliente> cartoes) {
	super();
	this.cliente = cliente;
	this.cartoes = cartoes;
    }
    
    public SituacaoCliente(Builder builder) {
	this.cliente = builder.cliente;
	this.cartoes = builder.cartoes;
    }

    public SituacaoCliente() {
	super();
    }

    public DadosCliente getCliente() {
	return cliente;
    }

    public void setCliente(DadosCliente cliente) {
	this.cliente = cliente;
    }

    public List<CartaoCliente> getCartoes() {
	return cartoes;
    }

    public void setCartoes(List<CartaoCliente> cartoes) {
	this.cartoes = cartoes;
    }

    public static class Builder {
	
	private DadosCliente cliente;

	private List<CartaoCliente> cartoes;
	
	public Builder cliente(DadosCliente cliente) {
	    this.cliente = cliente;
	    return this;
	}
	 
	public Builder cartoes(List<CartaoCliente> cartoes) {
	    this.cartoes = cartoes;
	    return this;
	}
	
	public SituacaoCliente build() {
	    return new SituacaoCliente(this);
	}
    }
}
