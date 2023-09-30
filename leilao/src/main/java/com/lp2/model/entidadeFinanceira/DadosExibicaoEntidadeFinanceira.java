package com.lp2.model.entidadeFinanceira;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Serializable
@Getter
@Setter
public class DadosExibicaoEntidadeFinanceira {

    private Long id;
    private String cnpj;
    private String razaoSocial;
}
