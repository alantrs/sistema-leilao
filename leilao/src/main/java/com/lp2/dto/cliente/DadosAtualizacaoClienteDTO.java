package com.lp2.dto.cliente;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Serdeable.Deserializable
@Getter
@Setter
public class DadosAtualizacaoClienteDTO {

    private String nome;
    private String documentoCpfCnpj;
}
