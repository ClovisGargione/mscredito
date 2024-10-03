package io.github.clovisgargione.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

public class DadosSolicitacaoEmissaoCartao {

    private Integer idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
    
    public DadosSolicitacaoEmissaoCartao() {
	super();
    }

    public DadosSolicitacaoEmissaoCartao(Integer idCartao, String cpf, String endereco, BigDecimal limiteLiberado) {
	super();
	this.idCartao = idCartao;
	this.cpf = cpf;
	this.endereco = endereco;
	this.limiteLiberado = limiteLiberado;
    }

    public Integer getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Integer idCartao) {
        this.idCartao = idCartao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limiteLiberado) {
        this.limiteLiberado = limiteLiberado;
    }
}
