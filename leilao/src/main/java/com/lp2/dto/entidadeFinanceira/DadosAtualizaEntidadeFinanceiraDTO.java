package com.lp2.dto.entidadeFinanceira;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizaEntidadeFinanceiraDTO {

    private String cnpj;
    private String razaoSocial;
}
