package com.lp2.model.lance;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosEntradaLanceDTO {

    private BigDecimal valor;
    private Long idCliente;
}
