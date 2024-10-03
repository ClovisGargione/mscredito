package io.github.clovisgargione.msclientes.application.representation;

import io.github.clovisgargione.msclientes.domain.Cliente;

public class ClienteSaveRequest {

    private String cpf;
    private String nome;
    private Integer idade;

    public ClienteSaveRequest() {
	super();
    }

    public ClienteSaveRequest(String cpf, String nome, Integer idade) {
	super();
	this.cpf = cpf;
	this.nome = nome;
	this.idade = idade;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Integer getIdade() {
	return idade;
    }

    public void setIdade(Integer idade) {
	this.idade = idade;
    }
    
    public Cliente toModel() {
	return new Cliente(cpf, nome, getIdade());
    }
}
