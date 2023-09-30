package com.lp2.model.entidadeFinanceira;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntidadeFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String razaoSocial;

    public EntidadeFinanceira(){}
    public EntidadeFinanceira(DadosEntradaEntidadeFinanceira cadastro){
        this.cnpj = cadastro.getCnpj();
        this.razaoSocial = cadastro.getRazaoSocial();
    }

    public EntidadeFinanceira(EntidadeFinanceira entidade, DadosAtualizaEntidadeFinanceira atualizacao){
        this.id = entidade.getId();
        this.cnpj = atualizacao.getCnpj() != null ? atualizacao.getCnpj() : entidade.getCnpj();
        this.razaoSocial = atualizacao.getRazaoSocial() != null ? atualizacao.getRazaoSocial() : entidade.getRazaoSocial();
    }

}
