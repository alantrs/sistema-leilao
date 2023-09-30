package com.lp2.model.entidadeFinanceira;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaEntidadeFinanceira {

    private String cnpj;
    private String razaoSocial;
}
