package io.github.clovisgargione.msavaliadorcredito.domain.model;

import java.util.List;

public class RetornoAvaliacaoCliente {

    private List<CartaoAprovado> cartoes;

    public RetornoAvaliacaoCliente() {
	super();
    }

    public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoes) {
	super();
	this.cartoes = cartoes;
    }

    public RetornoAvaliacaoCliente(Builder builder) {
	this.cartoes = builder.cartoes;
    }

    public List<CartaoAprovado> getCartoes() {
	return cartoes;
    }

    public void setCartoes(List<CartaoAprovado> cartoes) {
	this.cartoes = cartoes;
    }
    
    public static class Builder {
	
	private List<CartaoAprovado> cartoes;
	
	public Builder cartoes(List<CartaoAprovado> cartoes) {
	    this.cartoes = cartoes;
	    return this;
	}
	
	public RetornoAvaliacaoCliente build() {
	    return new RetornoAvaliacaoCliente(this);
	}
	
    }

}
