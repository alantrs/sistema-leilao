package com.lp2.model;

import com.lp2.dto.entidadeFinanceira.DadosAtualizaEntidadeFinanceiraDTO;
import com.lp2.dto.entidadeFinanceira.DadosEntradaEntidadeFinanceiraDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class EntidadeFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnpj;
    private String razaoSocial;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Leilao> leiloes;

    public EntidadeFinanceira(){}
    public EntidadeFinanceira(DadosEntradaEntidadeFinanceiraDTO cadastro){
        this.cnpj = cadastro.getCnpj();
        this.razaoSocial = cadastro.getRazaoSocial();
    }

    public EntidadeFinanceira(EntidadeFinanceira entidade, DadosAtualizaEntidadeFinanceiraDTO atualizacao){
        this.id = entidade.getId();
        this.cnpj = atualizacao.getCnpj() != null ? atualizacao.getCnpj() : entidade.getCnpj();
        this.razaoSocial = atualizacao.getRazaoSocial() != null ? atualizacao.getRazaoSocial() : entidade.getRazaoSocial();
    }

    @Override
    public String toString() {
        return "EntidadeFinanceira{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", leiloes=" + leiloes +
                '}';
    }
}
