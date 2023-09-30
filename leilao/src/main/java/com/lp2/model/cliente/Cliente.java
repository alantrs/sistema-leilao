package com.lp2.model.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String documentoCpfCnpj;
    public Cliente(){}

    public Cliente(DadosEntradaClienteDTO cadastro){
        this.nome = cadastro.getNome();
        this.documentoCpfCnpj = cadastro.getDocumentoCpfCnpj();
    }

    public  Cliente(Cliente cliente, DadosAtualizacaoClienteDTO atualizacao){
        this.id = cliente.getId();
        this.nome = atualizacao.getNome() != null ? atualizacao.getNome() : cliente.getNome();
        this.documentoCpfCnpj = atualizacao.getDocumentoCpfCnpj() != null ? atualizacao.getDocumentoCpfCnpj() : cliente.getDocumentoCpfCnpj();
    }
}
