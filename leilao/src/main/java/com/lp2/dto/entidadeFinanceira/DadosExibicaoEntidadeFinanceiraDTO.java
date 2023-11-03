package com.lp2.dto.entidadeFinanceira;

import com.lp2.model.EntidadeFinanceira;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoEntidadeFinanceiraDTO {

    private Long id;
    private String cnpj;
    private String razaoSocial;

    public DadosExibicaoEntidadeFinanceiraDTO(EntidadeFinanceira entidadeFinanceira){
        this.id = entidadeFinanceira.getId();
        this.cnpj = entidadeFinanceira.getCnpj();
        this.razaoSocial = entidadeFinanceira.getRazaoSocial();
    }
}
